package com.pixel.body;

import java.util.ArrayList;
import java.util.Arrays;

import com.pixels.entity.Entity;
import com.pixels.start.Pixels;

public class BodyBiped extends Body {

	public BodyBiped(Entity e, float w, float h, String name) {
		super(e, w, h, new ArrayList<BodyDirection>(Arrays.asList(
				new BodyDirection(new ArrayList<String>(Arrays.asList(
						(name + Pixels.t.separator + "front" + Pixels.t.separator + "shadow.png"),
						(name + Pixels.t.separator + "front" + Pixels.t.separator + "leftFoot.png"),
						(name + Pixels.t.separator + "front" + Pixels.t.separator + "rightFoot.png"),
						(name + Pixels.t.separator + "front" + Pixels.t.separator + "body.png"),
						(name + Pixels.t.separator + "front" + Pixels.t.separator + "leftHand.png"),
						(name + Pixels.t.separator + "front" + Pixels.t.separator + "rightHand.png"),
						(name + Pixels.t.separator + "front" + Pixels.t.separator + "head.png")
						))),
				new BodyDirection(new ArrayList<String>(Arrays.asList(
						(name + Pixels.t.separator + "back" + Pixels.t.separator + "shadow.png"),
						(name + Pixels.t.separator + "back" + Pixels.t.separator + "leftFoot.png"),
						(name + Pixels.t.separator + "back" + Pixels.t.separator + "rightFoot.png"),
						(name + Pixels.t.separator + "back" + Pixels.t.separator + "body.png"),
						(name + Pixels.t.separator + "back" + Pixels.t.separator + "leftHand.png"),
						(name + Pixels.t.separator + "back" + Pixels.t.separator + "rightHand.png"),
						(name + Pixels.t.separator + "back" + Pixels.t.separator + "head.png")
						))),
				new BodyDirection(new ArrayList<String>(Arrays.asList(
						(name + Pixels.t.separator + "side" + Pixels.t.separator + "shadow.png"),
						(name + Pixels.t.separator + "side" + Pixels.t.separator + "rightFoot.png"),
						(name + Pixels.t.separator + "side" + Pixels.t.separator + "rightHand.png"),
						(name + Pixels.t.separator + "side" + Pixels.t.separator + "body.png"),
						(name + Pixels.t.separator + "side" + Pixels.t.separator + "leftFoot.png"),
						(name + Pixels.t.separator + "side" + Pixels.t.separator + "leftHand.png"),
						(name + Pixels.t.separator + "side" + Pixels.t.separator + "head.png")
						))),
				new BodyDirection(new ArrayList<String>(Arrays.asList(
						(name + Pixels.t.separator + "frontSide" + Pixels.t.separator + "shadow.png"),
						(name + Pixels.t.separator + "frontSide" + Pixels.t.separator + "leftFoot.png"),
						(name + Pixels.t.separator + "frontSide" + Pixels.t.separator + "rightFoot.png"),
						(name + Pixels.t.separator + "frontSide" + Pixels.t.separator + "body.png"),
						(name + Pixels.t.separator + "frontSide" + Pixels.t.separator + "leftHand.png"),
						(name + Pixels.t.separator + "frontSide" + Pixels.t.separator + "rightHand.png"),
						(name + Pixels.t.separator + "frontSide" + Pixels.t.separator + "head.png")
						))),
				new BodyDirection(new ArrayList<String>(Arrays.asList(
						(name + Pixels.t.separator + "backSide" + Pixels.t.separator + "shadow.png"),
						(name + Pixels.t.separator + "backSide" + Pixels.t.separator + "leftFoot.png"),
						(name + Pixels.t.separator + "backSide" + Pixels.t.separator + "rightFoot.png"),
						(name + Pixels.t.separator + "backSide" + Pixels.t.separator + "rightHand.png"),
						(name + Pixels.t.separator + "backSide" + Pixels.t.separator + "body.png"),
						(name + Pixels.t.separator + "backSide" + Pixels.t.separator + "leftHand.png"),
						(name + Pixels.t.separator + "backSide" + Pixels.t.separator + "head.png")
						)))
			)));
	}
	
	public void setWalking(boolean w) {
		if (w != walking) {
			if (w) {
				setBaseAction(new ActionBipedWalking(this));
			} else {
				setBaseAction(null);
			}
		}
		super.setWalking(w);
	}
	
}
