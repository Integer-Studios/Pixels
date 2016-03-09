package com.pixels.entity;


import com.pixels.body.BodyBiped;

public class EntityGob extends EntityAlive {
	
	public EntityGob() {
		super();
		this.id = 3;
		body = new BodyBiped(this, 0.75f, 1.1875f, "gob");
	}

}
