package com.pixels.entity;


import org.newdawn.slick.GameContainer;

import com.pixel.input.KeyBinder;
import com.pixel.input.KeyBinding;
import com.pixel.input.KeyCode;
import com.pixel.input.KeyboardListener;
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
				
		this.setPosition(posX+velX, posY+velY);
		
		if (velX != 0 || velY != 0) {
			Pixels.client.addPacket(new PacketUpdatePlayer(this));
		}
		
		super.update(c, delta, w);

	}

	@Override
	public void onKeyDown(String name) {		
		if (name.equals("down")) {
			velY += 0.1f;
		}
		if (name.equals("up")) {
			velY -= 0.1f;
		}
		if (name.equals("left")) {
			velX -= 0.1f;
		}
		if (name.equals("right")) {
			velX += 0.1f;
		}
	}

	@Override
	public void onKeyUp(String name) {
		if (name.equals("down")) {
			velY -= 0.1f;
		}
		if (name.equals("up")) {
			velY += 0.1f;
		}
		if (name.equals("left")) {
			velX += 0.1f;
		}
		if (name.equals("right")) {
			velX -= 0.1f;
		}
	}
	
	public float velX, velY;

}
