package com.pixels.entity;


import org.newdawn.slick.GameContainer;

import com.pixel.input.KeyBinder;
import com.pixel.input.KeyBinding;
import com.pixel.input.KeyCode;
import com.pixel.input.KeyboardListener;
import com.pixels.packet.PacketMoveEntity;
import com.pixels.packet.PacketUpdatePlayer;
import com.pixels.start.Pixels;
import com.pixels.world.World;

public class EntityPlayer extends Entity implements KeyBinder {
	
	public EntityPlayer() {
		System.out.println("new one of these");
		this.id = 1;
		KeyboardListener.addKeyBinding(new KeyBinding("up", KeyCode.KEY_W, this));
		KeyboardListener.addKeyBinding(new KeyBinding("down", KeyCode.KEY_S, this));
		KeyboardListener.addKeyBinding(new KeyBinding("left", KeyCode.KEY_A, this));
		KeyboardListener.addKeyBinding(new KeyBinding("right", KeyCode.KEY_D, this));
	}

//	public EntityPlayer(float x, float y, boolean p) {
//		super(x, y, p);
//		this.id = 1;
//	}
	
	public void update(GameContainer c, int delta, World w) {
				
//		this.setPosition(posX+velX, posY+velY);
		
		if (velocityX != prevVelocityX || velocityY != prevVelocityY) {
			
			Pixels.client.addPacket(new PacketMoveEntity(this));
			
		}
		
		super.update(c, delta, w);

	}

	@Override
	public void onKeyDown(String name) {
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
	
	public float velX, velY;

}
