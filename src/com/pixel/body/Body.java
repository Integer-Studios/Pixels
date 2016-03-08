package com.pixel.body;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.entity.Entity;
import com.pixels.world.World;

public class Body {

	public Body(Entity e, float w, float h, ArrayList<BodyDirection> d) {
		//handles which direction to show, what actions are present
		entity = e;
		width = w;
		height = h;
		directions = d;
		actions = new ArrayList<Action>();
		//directions:
		// 0 front
		// 1 back
		// 2 left (flip right)
		// 3 front left (flip right)
		// 4 back left (flip right)
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		advanceActions();
		updateDirection();
		directions.get(currentDirection).render(this, c, g, w, shouldFlip);
	}
	
	public void advanceActions() {
		// in ascending order (0, 1, 2, ...) apply next frame of action
		//	for each direction
		//		set each body part's offset x and y
		for (Action a : actions) {
			if (a != null) {
				directions.get(currentDirection).applyOffsets(a.getNextXFrame(currentDirection), a.getNextYFrame(currentDirection));
			}
		}
	}

	public void updateDirection() {
		//based on entity velocity, set direction and set flip
		//temporary - will use entity velocity
		if (entity.velX > 0) {
			shouldFlip = true;
			if (entity.velY > 0) {
				// + x + y front right
				currentDirection = 3;
			} else if (entity.velY < 0) {
				// + x - y back right
				currentDirection = 4;
			} else {
				// + x 0 y right
				currentDirection = 2;
			}
		} else if (entity.velX < 0) {
			shouldFlip = false;
			if (entity.velY > 0) {
				// - x + y front left
				currentDirection = 3;
			} else if (entity.velY < 0) {
				// - x - y back left
				currentDirection = 4;
			} else {
				// - x 0 y left
				currentDirection = 2;
			}
		} else if (entity.velY > 0) {
			shouldFlip = false;
			// 0 x + y front
			currentDirection = 0;
		} else if (entity.velY < 0) {
			shouldFlip = false;
			// 0 x - y back
			currentDirection = 1;
		} else {
			// 0 x 0 y no change (keep at whatever last movement was)
		}
		
		if (entity.velX == 0 && entity.velY == 0) {
			setWalking(false);
		} else {
			setWalking(true);
		}
		
	}
	
	public void setWalking(boolean w) {
		walking = w;
	}
	
	public void addAction(Action a) {
		//adds auxillary action (punching, swinging, flipping off)
		actions.add(a);
	}
	
	public void setBaseAction(Action a) {
		//changes index 0 of actions (set walking or standing)
		if (actions.size() > 0)
			actions.set(0, a);
		else
			actions.add(a);
	}
	
	public void clearActions() {
		//clears all actions (stops animation)
		actions = new ArrayList<Action>();
	}
	
	public void clearAuxillaryActions() {
		//clears all actions except the base (walking or standing) action
		for (int i = 1; i < actions.size(); i++) {
			actions.remove(i);
		}
	}
	
	public Entity entity;
	public ArrayList<Action> actions;
	public ArrayList<BodyDirection> directions;
	public int currentDirection = 0;
	public float width, height;
	public boolean shouldFlip = false;
	public boolean walking = false;
		
}
