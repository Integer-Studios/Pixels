package com.pixels.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.body.BodyBiped;
import com.pixels.world.World;

public class EntityOnlinePlayer extends Entity {
	
	public EntityOnlinePlayer() {
		this.id = 2;
		body = new BodyBiped(this, 0.875f, 1.3125f, "rob");
	}

	public void render(GameContainer c, Graphics g, World w) {
		body.render(c, g, w);
	}
	
//	public EntityOnlinePlayer(float x, float y, boolean p) {
//		super(x, y, p);
//		this.id = 2;
//	}
	
	public BodyBiped body;

}
