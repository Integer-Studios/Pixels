package com.pixels.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.start.Pixels;
import com.pixels.world.World;

public class Entity {

	public Entity(int x, int y) {
		id = 0;
		posX = x;
		posY = y;
		serverID = Pixels.world.propogateEntity(this);
	}

	public void update(World w) {
		
	}
	
	public void render(GameContainer c, Graphics g) {

	}

	public int getServerID() {
		return serverID;
	}
	
	public int id, posX, posY, serverID;

}
