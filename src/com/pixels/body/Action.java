package com.pixels.body;

import java.util.ArrayList;

public class Action {
	
	public Action(ArrayList<ActionDirection> d, int f, int s, Body b) {
		directions = d;
		frames = f;
		speed = s;
		body = b;
	}
	
	public float[] getNextXFrame(int direction) {
		return directions.get(direction).getXframe(currentFrame);
		
	}
	
	public float[] getNextYFrame(int direction) {
		return directions.get(direction).getYframe(currentFrame);
	}
	
	public void update() {
		tickCounter++;
		if (tickCounter == speed) {
			currentFrame++;
			if (currentFrame == frames)
				currentFrame = 0;
			tickCounter = 0;
		}
	}
	
	ArrayList<ActionDirection> directions;
	int tickCounter = 0;
	int currentFrame = 0;
	int frames, speed;
	Body body;
}
