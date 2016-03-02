package com.pixels.world;

import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.piece.Piece;
import com.pixels.tile.Tile;

public class Chunk {
	
	public Chunk(int x, int y) {
		chunkX = x;
		chunkY = y;
	}

	public void render(GameContainer c, Graphics g, World w) {
		for (int i = 0; i < 256; i++) {
			tiles.get(i).render(c, g, w);
			Piece p = pieces.get(i);
			if (p != null)
				p.render(c, g, w);
		}
	}
	
	public void update(GameContainer c, int delta, World w) {
		for (int i = 0; i < 256; i++) {
			tiles.get(i).update(c, delta, w);
			Piece p = pieces.get(i);
			if (p != null)
				p.update(c, delta, w);
		}
	}
	
	public void setPieceID(int x, int y, int id) {
		pieces.get(getGlobalLocationIndex(x, y)).setPieceID(id);
	}
	
	public int getPieceID(int x, int y) {
		return pieces.get(getGlobalLocationIndex(x, y)).getPieceID();
	}
	
	private int getGlobalLocationIndex(int x, int y) {
		int localX = x - (chunkX << 4);
		int localY = y - (chunkY << 4);
		return getLocalLocationIndex(localX, localY);
	}
	
	private int getLocalLocationIndex(int x, int y) {
		return y*16 + x;
	}
	
	public ConcurrentHashMap<Integer,Tile> tiles = new ConcurrentHashMap<Integer,Tile>();
	public ConcurrentHashMap<Integer,Piece> pieces = new ConcurrentHashMap<Integer,Piece>();
	
	public int chunkX, chunkY;
}
