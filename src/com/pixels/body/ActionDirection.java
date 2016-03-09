package com.pixels.body;

public class ActionDirection {
	
	public ActionDirection(float[][] x, float[][] y) {
		xFrames = x;
		yFrames = y;
	}

	public float[] getXframe(int frame) {
		return xFrames[frame];
	}
	
	public float[] getYframe(int frame) {
		return yFrames[frame];
	}
	
//	effects only x images out of n
//	for each of 8 direction 
//		for each of x (out of n) images 
//			an array of x and y offsets
	
	// [frames][body parts]
	float [][] xFrames;
	float [][] yFrames;
	
}
