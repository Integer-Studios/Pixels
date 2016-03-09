package com.pixels.entity;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixel.body.BodyBiped;
import com.pixels.world.World;

public class EntityGob extends Entity {
	
	public EntityGob() {
		this.id = 4;
		Random r = new Random();
		float mult;
		if (r.nextInt(5) == 0) {
			//giant
			mult = (r.nextInt(10) + 10) * 0.1f;
		} else if (r.nextInt(5) == 0) {
			//small
			mult = (r.nextInt(5) + 5) * 0.1f;
		} else {
			//normal
			mult = 1f;
		}
		body = new BodyBiped(this, 0.75f * mult, 1.1875f * mult, "gob");
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		body.render(c, g, w);
	}
	
	public BodyBiped body;


}
