package com.pixels.entity;


import java.util.Random;

import org.newdawn.slick.GameContainer;

import com.pixels.body.ActionBipedPunch;
import com.pixels.body.ActionBipedPunchContinuous;
import com.pixels.body.BodyBiped;
import com.pixels.gui.GUIDamageIndicator;
import com.pixels.input.InterfaceManager;
import com.pixels.input.KeyBinder;
import com.pixels.input.KeyBinding;
import com.pixels.input.KeyCode;
import com.pixels.input.SimpleMouseListener;
import com.pixels.item.Inventory;
import com.pixels.packet.PacketMoveEntity;
import com.pixels.piece.Piece;
import com.pixels.start.Pixels;
import com.pixels.tile.Tile;
import com.pixels.world.World;

public class EntityPlayer extends EntityAlive implements KeyBinder, SimpleMouseListener {
	
	public EntityPlayer() {
		super();
		this.id = 1;
		Random r = new Random();
		if (r.nextInt(2) == 0) {
			body = new BodyBiped(this, 0.875f, 1.3125f, "rob");
		} else {
			body = new BodyBiped(this, 0.875f, 1.3125f, "zob");
		}
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("punch", KeyCode.KEY_P, this));
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("up", KeyCode.KEY_W, this));
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("down", KeyCode.KEY_S, this));
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("left", KeyCode.KEY_A, this));
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("right", KeyCode.KEY_D, this));
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("e-up", KeyCode.KEY_U, this));
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("e-down", KeyCode.KEY_J, this));
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("stats", KeyCode.KEY_B, this));

		InterfaceManager.worldInterface.addSimpleListner(this);
		inventory = new Inventory(4, 4, 8);
	}
	
	public boolean isInReach(float x, float y) {
		return (posX+reach > x && posX-reach < x && posY+reach > y && posY-reach < y);
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void update(GameContainer c, int delta, World w) {
		
		updateInteraction(w);
		updateInputBindings(w);
			
		super.update(c, delta, w);


	}
	
	private void updateInputBindings(World w) {
		if (eUp) {
			w.setElevation((int)posX, (int)posY, w.getElevation((int)posX, (int)posY)+1);
			eUp = false;
		}
		
		if (eDown) {
			w.setElevation((int)posX, (int)posY, w.getElevation((int)posX, (int)posY)-1);
			eDown = false;
		}
		
		if (stats) {
			Tile t = w.getTile((int)posX, (int)posY);
			System.out.println("e:" + t.elevation + ", h:" + t.humidity + ", t:" + t.tempurature);
			stats = false;
		}
		
//		System.out.println(w.getElevation((int)posX, (int)posY));
		
		if (mouseDown) {
			int x = w.UIToWorldCoordX(mouseX);
			int y = w.UIToWorldCoordY(mouseY);
			if (isInReach(x, y)) {
				startInteraction(w, x, y);
			}
			mouseDown = false;
		}
		
		if (mouseUp) {
			if (interactionPiece != null)
				endInteraction();
			mouseUp = false;
		}
		
		if (up && !down) {
			if (left && !right) {
				velocityX = -0.07071f;
				velocityY = -0.07071f;
			} else if (!left && right) {
				velocityX = 0.07071f;
				velocityY = -0.07071f;
			} else {
				velocityY = -0.1f;
				velocityX = 0f;
			}
		} else if (!up && down) {
			if (left && !right) {
				velocityX = -0.07071f;
				velocityY = 0.07071f;
			} else if (!left && right) {
				velocityX = 0.07071f;
				velocityY = 0.07071f;
			} else {
				velocityY = 0.1f;
				velocityX = 0f;
			}
		} else {
			if (left && !right) {
				velocityX = -0.1f;
				velocityY = 0f;
			} else if (!left && right) {
				velocityX = 0.1f;
				velocityY = 0f;
			} else {
				velocityY = 0f;
				velocityX = 0f;
			}
		}
		
//		System.out.println("player: " + velocityX + ", " + velocityY + "/" + prevVelocityX + "," + prevVelocityY);

						
		if (velocityX != prevVelocityX || velocityY != prevVelocityY) {
			Pixels.client.addPacket(new PacketMoveEntity(this));
		}
	}
		
	private void startInteraction(World w, int x, int y) {
		interactionPiece = w.getPiece(x, y);
		
		if (interactionPiece == null)
			return;
		
		if (interactionPiece.getMaxDamage() == -1) {
			interactionPiece = null;
			return;
		}
		
		body.addAction(new ActionBipedPunchContinuous(body));
		damageIndicator = new GUIDamageIndicator(this, interactionPiece);
		Pixels.gui.addComponent(damageIndicator);
	}
	
	private void updateInteraction(World w) {
		if (interactionPiece!=null) {
			currentPieceDamage+=damageIncrement;
			if (interactionPiece.getMaxDamage() == currentPieceDamage) {
				interactionPiece.harvest(w);
				endInteraction();
			}
		}
	}

	private void endInteraction() {
		Pixels.gui.removeComponent(damageIndicator);
		damageIndicator = null;
		body.clearAuxillaryActions();
		interactionPiece = null;
		currentPieceDamage = 0;
	}

	@Override
	public void onKeyDown(String name) {
		if (name.equals("punch")) {
			body.addAction(new ActionBipedPunch(body));
		}
		if (name.equals("down")) {
			down = true;
		}
		if (name.equals("up")) {
			up = true;
		}
		if (name.equals("left")) {
			left = true;
		}
		if (name.equals("right")) {
			right = true;
		}
	}
	
	
	@Override
	public void onKeyUp(String name) {
		if (name.equals("down")) {
			down = false;
		}
		if (name.equals("up")) {
			up = false;
		}
		if (name.equals("left")) {
			left = false;
		}
		if (name.equals("right")) {
			right = false;
		}
		
		if (name.equals("e-up")) {
			eUp = true;
		}
		if (name.equals("e-down")) {
			eDown = true;
		}
		if (name.equals("stats")) {
			stats = true;
		}
	}	
	
	@Override
	public void mouseDown(int button, int x, int y) {
		// TODO Auto-generated method stub
		mouseX = x;
		mouseY = y;
		mouseDown = true;
		mouseUp = false;
	}

	@Override
	public void mouseUp(int button, int x, int y) {
		// TODO Auto-generated method stub
		mouseX = x;
		mouseY = y;
		mouseUp = true;
		mouseDown = false;
	}
	
	public boolean up, down, left, right, mouseDown, mouseUp, eUp, eDown, stats;
	public int currentPieceDamage = 0;
	//this should be decided by the tool or something
	public int damageIncrement = 1;
	public int mouseX, mouseY;
	public float reach = 3f;
	public Piece interactionPiece;
	public GUIDamageIndicator damageIndicator;
	public Inventory inventory;

}
