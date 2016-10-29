package com.pixels.tile;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.start.Pixels;
import com.pixels.tile.TileInfo;
import com.pixels.world.World;

public class Tile {

	public Tile(int x, int y, int id, int e, int h, int t) {
		
		posX = x;
		posY = y;
		elevation = e;
		humidity = h;
		tempurature = t;
		this.id = id;
		
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		info.get(id).render(c, g, w, this);
	}
	
	public void update(GameContainer c, int delta, World w) {
		info.get(id).update(c, delta, w, this);
	}
	
	public boolean isCliff(World w) {
		int e = elevation;
		int eDown = w.getElevation(posX, posY+1) - e;
		return (eDown < 0);
	}
	
	public int posX, posY, id, elevation, humidity, tempurature;
	public static ArrayList<TileInfo> info = new ArrayList<TileInfo>();
	
	static {
		String s = Pixels.t.separator;
		info.add(new TileGrass());
		info.add(new TileInfo(s+"tiles"+s+"water"));
		info.add(new TileInfo(s+"tiles"+s+"sand"));
	}

}
