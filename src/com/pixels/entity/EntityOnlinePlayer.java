package com.pixels.entity;

import java.util.Random;

import com.pixels.body.BodyBiped;

public class EntityOnlinePlayer extends EntityAlive {
	
	public EntityOnlinePlayer() {
		super();
		this.id = 2;
		Random r = new Random();

		if (r.nextInt(2) == 0) {
			body = new BodyBiped(this, 0.875f, 1.3125f, "rob");
		} else {
			body = new BodyBiped(this, 0.875f, 1.3125f, "zob");
		}
	}

}
