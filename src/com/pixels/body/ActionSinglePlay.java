package com.pixels.body;

import java.util.ArrayList;

public class ActionSinglePlay extends Action {

	public ActionSinglePlay(ArrayList<ActionDirection> d, int f, int s, Body b) {
		super(d, f, s, b);
	}
	
	public void update() {
		tickCounter++;
		if (tickCounter == speed) {
			currentFrame++;
			if (currentFrame == frames)
				this.body.clearAuxillaryActions();
			tickCounter = 0;
		}
	}

}
