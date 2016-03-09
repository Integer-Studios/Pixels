package com.pixels.entity;


import org.newdawn.slick.GameContainer;

import com.pixels.body.ActionBipedPunch;
import com.pixels.body.BodyBiped;
import com.pixels.input.KeyBinder;
import com.pixels.input.KeyBinding;
import com.pixels.input.KeyCode;
import com.pixels.input.KeyboardListener;
import com.pixels.packet.PacketMoveEntity;
import com.pixels.piece.Piece;
import com.pixels.start.Pixels;
import com.pixels.world.World;

public class EntityPlayer extends EntityAlive implements KeyBinder {
	
	public EntityPlayer() {
		super();
		System.out.println("new one of these");
		this.id = 1;
		body = new BodyBiped(this, 0.875f, 1.3125f, "rob");
		KeyboardListener.addKeyBinding(new KeyBinding("punch", KeyCode.KEY_P, this));
		KeyboardListener.addKeyBinding(new KeyBinding("up", KeyCode.KEY_W, this));
		KeyboardListener.addKeyBinding(new KeyBinding("down", KeyCode.KEY_S, this));
		KeyboardListener.addKeyBinding(new KeyBinding("left", KeyCode.KEY_A, this));
		KeyboardListener.addKeyBinding(new KeyBinding("right", KeyCode.KEY_D, this));
	}

	public void update(GameContainer c, int delta, World w) {
				
		if (velocityX != prevVelocityX || velocityY != prevVelocityY) {
			
//			System.out.println("A");
			Pixels.client.addPacket(new PacketMoveEntity(this));
			
		}
		Piece p = w.getPiece((int)posX, (int)posY);
		if (p != null && p.id == 3) {
			if (p.collisionBox.intersects(collisionBox))
				System.out.println("colliding");
		}
		
		super.update(c, delta, w);

	}

	@Override
	public void onKeyDown(String name) {
		if (name.equals("punch")) {
			body.addAction(new ActionBipedPunch(body));
		}
		if (name.equals("down")) {
			velocityY += 0.1f;
		}
		if (name.equals("up")) {
			velocityY -= 0.1f;
		}
		if (name.equals("left")) {
			velocityX -= 0.1f;
		}
		if (name.equals("right")) {
			velocityX += 0.1f;
		}
		if (velocityX != 0 && velocityY != 0) {
			
			if (velocityX < 0) {
				velocityX = -.07071F;
			} else {
				velocityX = .07071F;
			}
			if (velocityY < 0) {
				velocityY = -.07071F;
			} else {
				velocityY = .07071F;
			}
			
		}
	}
	
	
	@Override
	public void onKeyUp(String name) {
		if (name.equals("down")) {
			velocityY = 0F;
		}
		if (name.equals("up")) {
			velocityY = 0F;
		}
		if (name.equals("left")) {
			velocityX = 0F;
		}
		if (name.equals("right")) {
			velocityX = 0F;
		}
		
	}	

}
