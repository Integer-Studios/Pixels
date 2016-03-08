package com.pixels.tile;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.start.Pixels;
import com.pixels.tile.TileInfo;
import com.pixels.util.Toolkit;
import com.pixels.world.World;

public class Tile {

	public Tile(int x, int y, int id) {
		
		posX = x;
		posY = y;
		this.id = id;
		
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		info.get(id).render(c, g, w, this);
	}
	
	public void update(GameContainer c, int delta, World w) {
		info.get(id).update(c, delta, w, this);
	}
	
	public int posX, posY, id;
	public static ArrayList<TileInfo> info = new ArrayList<TileInfo>();
	
	static {
		String s = Pixels.t.separator;
		info.add(new TileInfo(s+"tiles"+s+"grass.png"));
	}

}
