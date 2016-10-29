package com.pixels.tile;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.piece.Piece;
import com.pixels.start.Pixels;
import com.pixels.util.TextureLoader;
import com.pixels.world.World;


public class TileInfo {
		
	public TileInfo(String t) {
		basePath = t;
		upOverlays = new HashMap<Integer, Image>();
		upTextures = new String[]{"top.png", "bottom.png", "left.png", "right.png", "top-left.png", "top-right.png", "bottom-left.png", "bottom-right.png"};
		downOverlays = new HashMap<Integer, Image>();
		downTextures = new String[]{"top.png", "bottom.png", "left.png", "right.png", "bottom-left.png", "bottom-right.png"};
	}
	
	public void update(GameContainer c, int delta, World w, Tile t) {

	}
	
	public void render(GameContainer c, Graphics g, World w, Tile t) {

		renderBase(w, t);
		
		int e = t.elevation;
		
		int eUp = w.getElevation(t.posX, t.posY-1) - e;
		int eDown = w.getElevation(t.posX, t.posY+1) - e;
		int eLeft = w.getElevation(t.posX-1, t.posY) - e;
		int eRight = w.getElevation(t.posX+1, t.posY) - e;
		
//		down Overlays
		if (eUp < 0) {
			renderDownOverlay(w, t, 0);
		}
		if (eDown < 0) {
			renderDownOverlay(w, t, 1);
		}
		if (eLeft < 0) {
			renderDownOverlay(w, t, 2);
			if (eDown < 0) 
				renderDownOverlay(w, t, 4);
		}
		if (eRight < 0) {
			renderDownOverlay(w, t, 3);
			if (eDown < 0) 
				renderDownOverlay(w, t, 5);
		}
		
//		up Overlays
		boolean tl = false, tr = false, bl = false, br = false;
		if (eUp > 0) {
			renderUpOverlay(w, t, 0);
			renderUpOverlay(w, t, 4);
			renderUpOverlay(w, t, 5);
			tl = true;
			tr = true;
		}
		if (eDown > 0) {
			renderUpOverlay(w, t, 1);
			renderUpOverlay(w, t, 6);
			renderUpOverlay(w, t, 7);
			bl = true;
			br = true;
		}
		if (eLeft > 0) {
			renderUpOverlay(w, t, 2);
			if (!tl)
				renderUpOverlay(w, t, 4);
			if (!bl)
				renderUpOverlay(w, t, 6);
		}
		if (eRight > 0) {
			renderUpOverlay(w, t, 3);
			if (!tr)
				renderUpOverlay(w, t, 5);
			if (!br)
				renderUpOverlay(w, t, 7);
		}

	}
	
	protected void renderBase(World w, Tile t) {
		if (base == null)  {
			base = TextureLoader.load(basePath + Pixels.t.separator + "base.png");
		}
		base.draw(t.posX*w.tileConstant+w.globalOffsetX, t.posY*w.tileConstant+w.globalOffsetY, w.tileConstant, w.tileConstant);
	}
	
	protected void renderUpOverlay(World w, Tile t, int i) {
		
		if (!upOverlays.containsKey(i) || upOverlays.get(i) == null) {
			upOverlays.put(i, TextureLoader.load(getOverlayTexture(true, i)));
		}
		
		upOverlays.get(i).draw(t.posX*w.tileConstant+w.globalOffsetX, t.posY*w.tileConstant+w.globalOffsetY, w.tileConstant, w.tileConstant);
	}
	
	protected void renderDownOverlay(World w, Tile t, int i) {
		
		if (!downOverlays.containsKey(i) || downOverlays.get(i) == null) {
			downOverlays.put(i, TextureLoader.load(getOverlayTexture(false, i)));
		}
		
		downOverlays.get(i).draw(t.posX*w.tileConstant+w.globalOffsetX, t.posY*w.tileConstant+w.globalOffsetY, w.tileConstant, w.tileConstant);
	}

	protected String getOverlayTexture(boolean up, int i) {
		String s = Pixels.t.separator;
		if (up)
			return basePath + s + "up" + s + upTextures[i];
		else
			return basePath + s + "down" + s + downTextures[i];
	}

	public Image base;
	public String basePath;
	public HashMap<Integer, Image> upOverlays;
	public String[] upTextures;
	public HashMap<Integer, Image> downOverlays;
	public String[] downTextures;

}
