package com.pixel.body;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.start.Pixels;
import com.pixels.util.TextureLoader;
import com.pixels.world.World;

public class BodyDirection {
	
//	n images in correct layer order
//	current offsets for each image (set by actions)
//
//	has a reset function for offsets
	
	public BodyDirection(ArrayList<String> bodyParts) {
		bodyPartTextures = bodyParts;
		xOffsets = new float[bodyPartTextures.size()];
		yOffsets = new float[bodyPartTextures.size()];
	}
	
	public void render(Body body, GameContainer c, Graphics g, World w, boolean shouldFlip) {
		
		if (bodyPartImages == null) {
			bodyPartImages = new ArrayList<Image>();
			for (String path : bodyPartTextures) {
				bodyPartImages.add(TextureLoader.load(Pixels.t.separator + "entities" + Pixels.t.separator + path));
			}
		}
		
		setFlipped(shouldFlip);
		directOffsets();

		
		int index = 0;
		
		for (Image image : bodyPartImages) {
			
			float width = w.tileConstant*body.width;
			float height = w.tileConstant*body.height;
			float x = body.entity.posX*w.tileConstant+w.globalOffsetX-(width/2) + xOffsets[index]*w.tileConstant;
			float y = body.entity.posY*w.tileConstant+w.globalOffsetY-(height/2) + yOffsets[index]*w.tileConstant;
			image.draw(x, y, width, height);
			
			index++;
			
		}
		
		xOffsets = new float[bodyPartTextures.size()];
		yOffsets = new float[bodyPartTextures.size()];
		
	}
	
	private void directOffsets() {
		if (flip) {
			for (int i = 0; i < xOffsets.length; i++) {
				xOffsets[i] = -1 * xOffsets[i];
	
			}
		}
	}

	public void setFlipped(boolean f) {

		if (f != flip) {
			for (int i = 0; i < bodyPartImages.size(); i++) {
				bodyPartImages.set(i, bodyPartImages.get(i).getFlippedCopy(true, false));
			}
		}
		
		flip = f;
	}
	
	public void applyOffsets(float[] x, float[] y) {
		for (int i = 0; i < xOffsets.length; i++) {
			xOffsets[i] += x[i];
			yOffsets[i] += y[i];
		}
	}
	
	public ArrayList<String> bodyPartTextures;
	public ArrayList<Image> bodyPartImages;
	public float[] xOffsets;
	public float[] yOffsets;
	public boolean flip = false;
	
}
