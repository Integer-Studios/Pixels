package com.pixels.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.util.TextureLoader;
import com.pixels.world.World;

public class PieceInfoTall extends PieceInfo {
	
	public PieceInfoTall(String t, int h) {
		super(t);
		height = h;
	}
	
	public void render(GameContainer c, Graphics g, World w, Piece p) {

		if (texture == null)
			return;
		
		if (image == null)  {
			image = TextureLoader.load(texture);
		}
		
		image.draw(p.posX*w.tileConstant+w.globalOffsetX, p.posY*w.tileConstant+w.globalOffsetY-(w.tileConstant*(height-1)), w.tileConstant, w.tileConstant*height);

	}
	
	public int height;

}
