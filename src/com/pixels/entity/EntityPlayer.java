package com.pixels.entity;


import java.util.Random;

import org.newdawn.slick.GameContainer;

import com.pixels.body.ActionBipedPunch;
import com.pixels.body.BodyBiped;
import com.pixels.input.KeyBinder;
import com.pixels.input.KeyBinding;
import com.pixels.input.KeyCode;
import com.pixels.input.KeyboardListener;
import com.pixels.packet.PacketMoveEntity;
import com.pixels.start.Pixels;
import com.pixels.world.World;

public class EntityPlayer extends EntityAlive implements KeyBinder {
	
	public EntityPlayer() {
		super();
		this.id = 1;
		Random r = new Random();
		if (r.nextInt(2) == 0) {
			body = new BodyBiped(this, 0.875f, 1.3125f, "rob");
		} else {
			body = new BodyBiped(this, 0.875f, 1.3125f, "zob");
		}
		KeyboardListener.addKeyBinding(new KeyBinding("punch", KeyCode.KEY_P, this));
		KeyboardListener.addKeyBinding(new KeyBinding("up", KeyCode.KEY_W, this));
		KeyboardListener.addKeyBinding(new KeyBinding("down", KeyCode.KEY_S, this));
		KeyboardListener.addKeyBinding(new KeyBinding("left", KeyCode.KEY_A, this));
		KeyboardListener.addKeyBinding(new KeyBinding("right", KeyCode.KEY_D, this));
	}

	public void update(GameContainer c, int delta, World w) {
		
		if (up && !down) {
			if (left && !right) {
				velocityX = -0.07071f;
				velocityY = -0.07071f;
			} else if (!left && right) {
				velocityX = 0.07071f;
				velocityY = -0.07071f;
			} else {
				velocityY = -0.1f;
				velocityX = 0f;
			}
		} else if (!up && down) {
			if (left && !right) {
				velocityX = -0.07071f;
				velocityY = 0.07071f;
			} else if (!left && right) {
				velocityX = 0.07071f;
				velocityY = 0.07071f;
			} else {
				velocityY = 0.1f;
				velocityX = 0f;
			}
		} else {
			if (left && !right) {
				velocityX = -0.1f;
				velocityY = 0f;
			} else if (!left && right) {
				velocityX = 0.1f;
				velocityY = 0f;
			} else {
				velocityY = 0f;
				velocityX = 0f;
			}
		}
		
		w.checkEntityCollisions(this);
				
		if (velocityX != prevVelocityX || velocityY != prevVelocityY) {
			
			Pixels.client.addPacket(new PacketMoveEntity(this));
			
		}
		
		super.update(c, delta, w);

	}

	@Override
	public void onKeyDown(String name) {
		if (name.equals("punch")) {
			body.addAction(new ActionBipedPunch(body));
		}
		if (name.equals("down")) {
			down = true;
		}
		if (name.equals("up")) {
			up = true;
		}
		if (name.equals("left")) {
			left = true;
		}
		if (name.equals("right")) {
			right = true;
		}
	}
	
	
	@Override
	public void onKeyUp(String name) {
		if (name.equals("down")) {
			down = false;
		}
		if (name.equals("up")) {
			up = false;
		}
		if (name.equals("left")) {
			left = false;
		}
		if (name.equals("right")) {
			right = false;
		}
		
	}	
	
	public boolean up, down, left, right;

}
