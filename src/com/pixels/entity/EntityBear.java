package com.pixels.entity;

import com.pixels.body.BodyQuadruped;

public class EntityBear extends EntityAlive {
	
	public EntityBear() {
		super();
		this.id = 4;
		body = new BodyQuadruped(this, 1.5f, 1.448f, "bear");
	}

}
