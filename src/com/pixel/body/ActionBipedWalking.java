package com.pixel.body;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionBipedWalking extends Action {

	public ActionBipedWalking(Body b) {
		super(new ArrayList<ActionDirection>(Arrays.asList(
				
				//front (shadow, leftFoot, rightFoot, body, leftHand, rightHand, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						},
						new float[][]{
							{0, 0, 0, 0, 0.02f, -0.02f, 0},
							{0, -0.03f, 0.05f, 0, 0, 0, 0}, 
							{0, 0, 0, 0, -0.02f, 0.02f, 0},
							{0, 0.05f, -0.03f, 0, 0, 0, 0}
						}
					),
				//back (shadow, leftFoot, rightFoot, body, leftHand, rightHand, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						},
						new float[][]{
							{0, 0, 0, 0, 0.02f, -0.02f, 0},
							{0, -0.03f, 0.05f, 0, 0, 0, 0}, 
							{0, 0, 0, 0, -0.02f, 0.02f, 0},
							{0, 0.05f, -0.03f, 0, 0, 0, 0}
						}
					),
				//side (shadow, rightFoot, rightHand, body, leftFoot, leftHand, head)
				new ActionDirection(
						new float[][]{
							{0, -0.01f, -0.03f, 0, 0.01f, 0.03f, 0}, 
							{0, -0.07f, 0, 0, 0.07f, 0, 0}, 
							{0, -0.01f, 0.03f, 0, 0.01f, -0.03f, 0},
							{0, 0.05f, 0, 0, -0.05f, 0, 0}
						},
						new float[][]{
							{0, -.03f, 0.02f, 0, 0.02f, -0.02f, 0}, 
							{0, -0.01f, 0, 0, 0.02f, 0, 0}, 
							{0, -0.01f, 0.02f, 0, 0, -0.02f, 0},
							{0, -0.01f, 0, 0, 0.02f, 0, 0}
						}
					),
				//frontSide (shadow, leftFoot, rightFoot, body, leftHand, rightHand, head)
				new ActionDirection(
						new float[][]{
							{0, -0.02f, 0.02f, 0, 0.02f, -0.02f, 0}, 
							{0, -0.04f, 0.04f, 0, 0, 0, 0}, 
							{0, -0.02f, 0.02f, 0, -0.02f, 0.02f, 0},
							{0, 0.02f, -0.02f, 0, 0, 0, 0}
						},
						new float[][]{
							{0, 0.02f, -0.02f, 0, -0.02f, 0.01f, 0}, 
							{0, 0.04f, -0.04f, 0, 0, -0.02f, 0}, 
							{0, 0.02f, -0.02f, 0, 0.02f, -0.05f, 0},
							{0, -0.02f, 0.02f, 0, 0, -0.02f, 0}
						}
					),
				//backSide (shadow, leftFoot, rightFoot, rightHand, body, leftHand, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, -0.05f, 0.05f, -0.03f, 0, 0.03f, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0.05f, -0.05f, 0.03f, 0, -0.03f, 0}
						},
						new float[][]{
							{0, 0.03f, -0.02f, 0, 0, 0, 0}, 
							{0, -0.02f, 0.03f, -0.02f, 0, 0.02f, 0}, 
							{0, 0.03f, -0.02f, 0, 0, 0, 0},
							{0, 0.08f, -0.07f, 0.02f, 0, -0.02f, 0}
						}
					)
		)), 4, 5, b);

	}

}
