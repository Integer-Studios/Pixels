package com.pixels.body;

import java.util.ArrayList;
import java.util.Arrays;

public class ActionQuadrupedWalking extends Action {

	public ActionQuadrupedWalking(Body b) {
super(new ArrayList<ActionDirection>(Arrays.asList(
				
				//front (shadow, rightHand, rightFoot, leftHand, leftFoor, body, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						},
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						}
					),
				//back (shadow, rightHand, leftHand, head, body, rightFoot, leftFoot)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						},
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						}
					),
				//side (shadow, rightFoot, rightHand, body, leftFoot, leftHand, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						},
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						}
					),
				//frontSide (shadow, rightHand, rightFoot, leftHand, leftFoot, body, head)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						},
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						}
					),
				//backSide (shadow, rightHand, leftHand, head, body, rightFoot, leftFoot)
				new ActionDirection(
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						},
						new float[][]{
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0}, 
							{0, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 0, 0}
						}
					)
		)), 4, 5, b);
	}

}
