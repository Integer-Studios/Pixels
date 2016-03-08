package com.pixel.body;

import java.util.ArrayList;

public class Action {
	
	public Action(ArrayList<ActionDirection> d, int f, int s) {
		directions = d;
		frames = f;
		speed = s;
	}
	
	public float[] getNextXFrame(int direction) {
		advanceTick();
		return directions.get(direction).getXframe(currentFrame);
		
	}
	
	public float[] getNextYFrame(int direction) {
		return directions.get(direction).getYframe(currentFrame);
	}
	
	private void advanceTick() {
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
}
