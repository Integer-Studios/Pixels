package com.pixels.entity;

import com.pixels.body.BodyBiped;

public class EntityOnlinePlayer extends EntityAlive {
	
	public EntityOnlinePlayer() {
		super();
		this.id = 2;
		body = new BodyBiped(this, 0.875f, 1.3125f, "rob");
	}

}
