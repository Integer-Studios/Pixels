package com.pixels.piece;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.util.TextureLoader;
import com.pixels.world.World;

public class PieceInfo {
	
	// if c width and c height are 0, it doesn't collide
	public PieceInfo() {
		this(null);
	}
	
	public PieceInfo(String t) {
		texture = t;
		collisionWidth = 0;
		collisionHeight = 0;
		doesCollide = false;
	}
	
	// collision dimensions are relative to one block
	public PieceInfo(String t, float width, float height) {
		texture = t;
		collisionWidth = width;
		collisionHeight = height;
		doesCollide = true;
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
	
	public PieceInfo setMaxDamage(int i) {
		maxDamage = i;
		return this;
	}

	public Image image;
	public String texture;
	public float collisionWidth, collisionHeight;
	public boolean doesCollide = false;
	public int maxDamage = 100;

}
