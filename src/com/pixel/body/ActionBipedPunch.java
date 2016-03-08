package com.pixel.body;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionBipedPunch extends ActionSinglePlay {

	public ActionBipedPunch(Body b) {
		super(new ArrayList<ActionDirection>(Arrays.asList(
				
				//front (shadow, leftFoot, rightFoot, body, leftHand, rightHand, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0.04f, 0.02f, 0}, 
							{0, 0, 0, 0, 0.01f, 0.01f, 0}, 
							{0, 0, 0, 0, -0.01f, 0.01f, 0},
							{0, 0, 0, 0, 0.01f, 0.01f, 0}
						},
						new float[][]{
							{0, 0, 0, 0, 0.13f, -0.01f, 0}, 
							{0, 0, 0, 0, 0.08f, -0.01f, 0}, 
							{0, 0, 0, 0, 0.04f, -0.01f, 0},
							{0, 0, 0, 0, 0.01f, -0.01f, 0}
						}
					),
				//back (shadow, leftFoot, rightFoot, body, leftHand, rightHand, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0.02f, -0.01f, 0}, 
							{0, 0, 0, 0, 0.01f, -0.01f, 0}, 
							{0, 0, 0, 0, 0.01f, -0.01f, 0},
							{0, 0, 0, 0, 0.01f, -0.01f, 0}
						},
						new float[][]{
							{0, 0, 0, 0, -0.16f, -0.01f, 0}, 
							{0, 0, 0, 0, -0.1f, -0.01f, 0}, 
							{0, 0, 0, 0, -0.06f, -0.01f, 0},
							{0, 0, 0, 0, -0.01f, -0.01f, 0}
						}
					),
				//side (shadow, rightFoot, rightHand, body, leftFoot, leftHand, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0.01f, 0, 0, -0.18f, 0}, 
							{0, 0, 0.01f, 0, 0, -0.11f, 0}, 
							{0, 0, 0.01f, 0, 0, -0.05f, 0},
							{0, 0, 0.01f, 0, 0, 0.02f, 0}
						},
						new float[][]{
							{0, 0, -0.01f, 0, 0, -0.01f, 0}, 
							{0, 0, -0.01f, 0, 0, -0.01f, 0}, 
							{0, 0, -0.01f, 0, 0, -0.01f, 0},
							{0, 0, -0.01f, 0, 0, -0.01f, 0}
						}
					),
				//frontSide (shadow, leftFoot, rightFoot, body, leftHand, rightHand, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, -0.1f, -0.01f, 0}, 
							{0, 0, 0, 0, -0.02f, -0.01f, 0}, 
							{0, 0, 0, 0, -0.01f, -0.01f, 0},
							{0, 0, 0, 0, -0.01f, -0.01f, 0}
						},
						new float[][]{
							{0, 0, 0, 0, 0.1f, -0.01f, 0}, 
							{0, 0, 0, 0, 0.05f, -0.01f, 0}, 
							{0, 0, 0, 0, 0.01f, -0.01f, 0},
							{0, 0, 0, 0, -0.01f, -0.01f, 0}
						}
					),
				//backSide (shadow, leftFoot, rightFoot, rightHand, body, leftHand, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, -0.01f, 0, -0.10f, 0}, 
							{0, 0, 0, -0.01f, 0, -0.05f, 0}, 
							{0, 0, 0, -0.01f, 0, -0.01f, 0},
							{0, 0, 0, -0.01f, 0, 0.01f, 0}
						},
						new float[][]{
							{0, 0, 0, 0.01f, 0, -0.10f, 0}, 
							{0, 0, 0, 0.01f, 0, -0.05f, 0}, 
							{0, 0, 0, 0.01f, 0, -0.01f, 0},
							{0, 0, 0, 0.01f, 0, 0.01f, 0}
						}
					)
		)), 4, 5, b);
		// TODO Auto-generated constructor stub
	}

}
