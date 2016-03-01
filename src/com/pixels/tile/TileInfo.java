package com.pixels.tile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.util.TextureLoader;
import com.pixels.world.World;


public class TileInfo {
		
	public TileInfo(String t) {
		texture = t;
	}
	
	public void render(GameContainer c, Graphics g, World w, Tile t) {

		if (image == null)  {
			image = TextureLoader.load(texture);
		}
		
		image.draw(t.posX*w.tileConstant+w.globalOffsetX, t.posY*w.tileConstant+w.globalOffsetY, w.tileConstant, w.tileConstant);

	}
	
	public void update(GameContainer c, int delta, World w, Tile t) {

	}

	public Image image;
	public String texture;

}
