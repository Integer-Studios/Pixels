package com.pixels.entity;


import java.util.Random;

import org.newdawn.slick.GameContainer;

import com.pixels.world.World;

public class EntityPlayer extends Entity {
	
	public EntityPlayer() {
		this.id = 1;
	}

	public EntityPlayer(int x, int y, boolean p) {
		super(x, y, p);
		this.id = 1;
	}
	
	public void update(GameContainer c, int delta, World w) {
		if (true) {
			Random r = new Random();
			if (r.nextInt(300) == 0) {
				this.setPosition(posX+1, posY, w);
			}
			if (r.nextInt(300) == 0) {
				this.setPosition(posX, posY+1, w);
			}
		}
	}

}
