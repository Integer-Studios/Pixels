package com.pixels.body;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionQuadrupedWalking extends Action {

	public ActionQuadrupedWalking(Body b) {
super(new ArrayList<ActionDirection>(Arrays.asList(
				
				//front (shadow, rightHand, rightFoot, leftHand, leftFoor, body, head)
				new ActionDirection(
						new float[][]{
							{0, 0,      0,      0,      0,      0, 0}, 
							{0, 0.02f,  0.02f,  0.01f,  0.01f,  0, -0.01f}, 
							{0, 0,      0,      0,      0,      0, 0},
							{0, -0.01f, -0.01f, -0.02f, -0.02f, 0, 0.01f}
						},
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0.01f, 0.03f,  0.03f,  -0.03f, -0.03f, -0.01f, 0.01f}, 
							{0.02f, 0,      0,      0,      0,      0,      0.02f},
							{0.01f, -0.03f, -0.03f, 0.03f,  0.03f,  -0.01f, 0.01f}
						}
					),
				//back (shadow, rightHand, leftHand, head, body, rightFoot, leftFoot)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, -0.01f, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0.01f, 0, 0, 0}
						},
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0.01f, -0.03f, 0.03f, 0, 0, 0.03f, -0.03f}, 
							{0.02f, 0, 0, 0, 0, 0, 0},
							{0.01f, 0.03f, -0.03f, 0, 0, -0.03f, 0.03f}
						}
					),
				//side (shadow, rightFoot, rightHand, body, leftFoot, leftHand, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0.03f, -0.03f, 0.02f, -0.03f, 0.05f, 0.02f}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, -0.03f, 0.05f, -0.02f, 0.03f, -0.03f, -0.02f}
						},
						new float[][]{
							{0, 0, -0.03f, 0, -0.03f, 0, 0.01f}, 
							{0, -0.02f, -0.02f, 0, -0.02f, -0.02f, 0.02f}, 
							{0, -0.03f, 0, 0, 0, -0.03f, 0.01f},
							{0, -0.02f, -0.02f, 0, -0.02f, -0.02f, 0}
						}
					),
				//frontSide (shadow, rightHand, rightFoot, leftHand, leftFoot, body, head)
				new ActionDirection(
						new float[][]{
							{0, -0.01f, 0, 0, 0, 0, 0}, 
							{0, -0.03f, 0.02f, 0.03f, -0.02f, 0, -0.01f}, 
							{0, -0.01f, 0, 0, 0, 0, 0},
							{0, 0.03f, -0.02f, -0.03f, 0.02f, 0, 0.01f}
						},
						new float[][]{
							{0, 0.01f, 0, 0, 0, 0, 0}, 
							{0, 0.03f, -0.02f, -0.03f, 0.02f, 0.01f, 0.01f}, 
							{0, -0.01f, 0, 0, 0, 0.02f, 0},
							{0, -0.03f, 0.02f, 0.03f, -0.02f, 0.01f, -0.01f}
						}
					),
				//backSide (shadow, rightHand, leftHand, head, body, rightFoot, leftFoot)
				new ActionDirection(
						new float[][]{
							{0, -0.03f, -0.01f, 0, 0, -0.01f, -0.03f}, 
							{0, -0.01f, 0.03f, 0.02f, 0.01f, 0.03f, -0.01f}, 
							{0, 0.03f, -0.01f, 0, 0, -0.01f, 0.03f},
							{0, -0.01f, -0.03f, -0.01f, 0, -0.03f, -0.01f}
						},
						new float[][]{
							{0, -0.03f, 0.01f, 0, 0, 0.01f, -0.03f}, 
							{0, -0.01f, 0.03f, 0.01f, 0.01f, 0.03f, -0.01f}, 
							{0, 0.03f, -0.01f, 0, 0, -0.01f, 0.03f},
							{0, 0.01f, -0.03f, -0.02f, 0, -0.03f, 0.01f}
						}
					)
		)), 4, 6, b);
	}

}
