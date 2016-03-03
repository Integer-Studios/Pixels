package com.pixels.entity;


import org.newdawn.slick.GameContainer;

import com.pixel.input.KeyBinder;
import com.pixel.input.KeyBinding;
import com.pixel.input.KeyCode;
import com.pixel.input.KeyboardListener;
import com.pixels.world.World;

public class EntityPlayer extends Entity implements KeyBinder {
	
	public EntityPlayer() {
		this.id = 1;
		KeyboardListener.addKeyBinding(new KeyBinding("up", KeyCode.KEY_W, this));
		KeyboardListener.addKeyBinding(new KeyBinding("down", KeyCode.KEY_S, this));
		KeyboardListener.addKeyBinding(new KeyBinding("left", KeyCode.KEY_A, this));
		KeyboardListener.addKeyBinding(new KeyBinding("right", KeyCode.KEY_D, this));
	}

	public EntityPlayer(int x, int y, boolean p) {
		super(x, y, p);
		this.id = 1;
	}
	
	public void update(GameContainer c, int delta, World w) {
		
		if (velX == 0 && velY == 0)
			return;
		
		this.setPosition(posX+velX, posY+velY, w);
		
		velX = 0;
		velY = 0;
	}

	@Override
	public void onKeyDown(String name) {
		// TODO Auto-generated method stub
		
		if (name.equals("down")) {
			velY = 1;
		}
		if (name.equals("up")) {
			velY = -1;
		}
		if (name.equals("left")) {
			velX = -1;
		}
		if (name.equals("right")) {
			velX = 1;
		}
	}

	@Override
	public void onKeyUp(String name) {
		velX = 0;
		velY = 0;
	}
	
	public int velX, velY;

}
