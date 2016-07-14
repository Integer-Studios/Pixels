package com.pixels.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.start.Pixels;
import com.pixels.util.TextureLoader;
import com.pixels.world.World;

public class PieceBuilding extends PieceTall {

	public PieceBuilding(String t) {
		super(Pixels.t.separator+"pieces"+Pixels.t.separator+"building"+Pixels.t.separator+t+Pixels.t.separator+"base.png", 2, 1.0f, 1.0f);
		name = t;
	}
	
	public void render(GameContainer c, Graphics g, World w, Piece p) {
		
		super.render(c, g, w, p);
			
		if (w.getPiece(p.posX-1, p.posY) != null && w.getPieceID(p.posX-1, p.posY) == p.getPieceID()) {
			//left overlay
			renderOverlay(w, p, 0);
		}
		
		if (w.getPiece(p.posX+1, p.posY) != null && w.getPieceID(p.posX+1, p.posY) == p.getPieceID()) {
			//right overlay
			renderOverlay( w, p, 1);
		}
		
		if (w.getPiece(p.posX, p.posY-1) != null && w.getPieceID(p.posX, p.posY-1) == p.getPieceID()) {
			//top overlay
			renderOverlay(w, p, 2);
		}
		
	}

	
	private void renderOverlay(World w, Piece p, int i) {
		if (i == 0) {
			
			if (leftCover == null)
				leftCover = TextureLoader.load(getLayerTexture(0));
			
			leftCover.draw(p.posX*w.tileConstant+w.globalOffsetX, p.posY*w.tileConstant+w.globalOffsetY-(w.tileConstant*(size-1)), w.tileConstant, w.tileConstant*size);
		}	
		if (i == 1) {
			
			if (rightCover == null)
				rightCover = TextureLoader.load(getLayerTexture(1));
			
			rightCover.draw(p.posX*w.tileConstant+w.globalOffsetX, p.posY*w.tileConstant+w.globalOffsetY-(w.tileConstant*(size-1)), w.tileConstant, w.tileConstant*size);
		}
		if (i == 2) {
			
			if (topCover == null)
				topCover = TextureLoader.load(getLayerTexture(2));
			
			topCover.draw(p.posX*w.tileConstant+w.globalOffsetX, p.posY*w.tileConstant+w.globalOffsetY-(w.tileConstant*(size-1)), w.tileConstant, w.tileConstant*size);
		}
	}
	
	private String getLayerTexture(int i) {
		String s = Pixels.t.separator;
		if (i == 0)
			return s+"pieces"+s+"building"+s+name+s+"left.png";
		if (i == 1)
			return s+"pieces"+s+"building"+s+name+s+"right.png";
		if (i == 2)
			return s+"pieces"+s+"building"+s+name+s+"top.png";
		else
			return "";
	}

	
	public Image leftCover;
	public Image rightCover;
	public Image topCover;
	
	public String name;

}
