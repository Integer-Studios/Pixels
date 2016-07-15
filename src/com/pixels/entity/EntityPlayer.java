package com.pixels.entity;


import java.util.Random;

import org.newdawn.slick.GameContainer;

import com.pixels.body.ActionBipedPunch;
import com.pixels.body.BodyBiped;
import com.pixels.input.KeyBinder;
import com.pixels.input.KeyBinding;
import com.pixels.input.KeyCode;
import com.pixels.input.KeyboardListener;
import com.pixels.input.MouseClickListener;
import com.pixels.input.SimpleMouseListener;
import com.pixels.packet.PacketMoveEntity;
import com.pixels.packet.PacketUpdatePiece;
import com.pixels.start.Pixels;
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
		KeyboardListener.addKeyBinding(new KeyBinding("punch", KeyCode.KEY_P, this));
		KeyboardListener.addKeyBinding(new KeyBinding("up", KeyCode.KEY_W, this));
		KeyboardListener.addKeyBinding(new KeyBinding("down", KeyCode.KEY_S, this));
		KeyboardListener.addKeyBinding(new KeyBinding("left", KeyCode.KEY_A, this));
		KeyboardListener.addKeyBinding(new KeyBinding("right", KeyCode.KEY_D, this));
		MouseClickListener.addSimpleListner(this);
	}
	
	public boolean isInReach(float x, float y) {
		return (posX+reach > x && posX-reach < x && posY+reach > y && posY-reach < y);
	}

	public void update(GameContainer c, int delta, World w) {
		
		if (mouseUp) {
			mouseX = w.UIToWorldCoordX(mouseX);
			mouseY = w.UIToWorldCoordY(mouseY);
			if (isInReach(mouseX, mouseY)) {
				interactWithPiece(w, mouseX, mouseY);
			}
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
						
		if (velocityX != prevVelocityX || velocityY != prevVelocityY) {
			
			Pixels.client.addPacket(new PacketMoveEntity(this));
			
		}
			
		super.update(c, delta, w);

	}

	public void interactWithPiece(World w, int x, int y) {
		if (w.getPieceID(x, y) == 9) {
			int meta = w.getPiece(x, y).metadata;
			meta++;
			if (meta > 2) {
				w.setPieceID(x, y, 0);
				Pixels.client.addPacket(new PacketUpdatePiece(w.getPiece(x, y)));
			} else {
				w.setPieceIDAndMetadata(x, y, 9, meta);
				Pixels.client.addPacket(new PacketUpdatePiece(w.getPiece(x, y)));
			}
		} else {
			w.setPieceID(x, y, 9);
			Pixels.client.addPacket(new PacketUpdatePiece(w.getPiece(x, y)));
		}
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
		
	}	
	
	@Override
	public void mouseDown(int button, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseUp(int button, int x, int y) {
		// TODO Auto-generated method stub
		mouseX = x;
		mouseY = y;
		mouseUp = true;
	}
	
	public boolean up, down, left, right, mouseUp;
	public int mouseX, mouseY;
	public float reach = 3f;

}
