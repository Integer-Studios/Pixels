package com.pixels.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.util.TextureLoader;
import com.pixels.world.World;

public class PieceInfoTall extends PieceInfo {
	
	public PieceInfoTall(String t, int h) {
		super(t);
		size = h;
	}
	
	public PieceInfoTall(String t, int h, float width, float height) {
		super(t, width, height);
		size = h;
	}
	
	public void render(GameContainer c, Graphics g, World w, Piece p) {

		if (texture == null)
			return;
		
		if (image == null)  {
			image = TextureLoader.load(texture);
		}
		
		image.draw(p.posX*w.tileConstant+w.globalOffsetX, p.posY*w.tileConstant+w.globalOffsetY-(w.tileConstant*(size-1)), w.tileConstant, w.tileConstant*size);

	}
	
	public int size;

}
