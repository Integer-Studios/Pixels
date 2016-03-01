package com.pixels.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.util.TextureLoader;
import com.pixels.world.World;

public class PieceInfo {
	
	public PieceInfo() {
		texture = null;
	}
	
	public PieceInfo(String t) {
		texture = t;
	}
	
	public void render(GameContainer c, Graphics g, World w, Piece p) {

		if (texture == null)
			return;
		
		if (image == null)  {
			image = TextureLoader.load(texture);
		}
		
		image.draw(p.posX*w.tileConstant+w.globalOffsetX, p.posY*w.tileConstant+w.globalOffsetY, w.tileConstant, w.tileConstant);

	}
	
	public void update(GameContainer c, int delta, World w, Piece p) {

	}

	public Image image;
	public String texture;

}
