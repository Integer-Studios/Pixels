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
		int oldX = posX;
		int oldY = posY;
		Random r = new Random();
		if (r.nextInt(50) == 0) {
			posX ++;
		}
		if (r.nextInt(50) == 0) {
			posY ++;
		}
		w.moveEntity(serverID, oldX, oldY, posX, posY);
	}

}
