package com.pixels.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.body.Body;
import com.pixels.world.World;

public class EntityAlive extends Entity {
	
	public EntityAlive() {
		super();
	}
	
	public Body body;
	
	public void render(GameContainer c, Graphics g, World w) {
		body.render(c, g, w);
	}

}
