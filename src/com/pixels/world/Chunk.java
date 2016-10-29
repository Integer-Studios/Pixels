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
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				
				int i = getLocalLocationIndex(x, y);
				
				tiles.get(i).render(c, g, w);
				Piece p = pieces.get(i);
				if (p != null)
					p.render(c, g, w);
				
//				Render is fucked
//				int globalX = (chunkX<<4)+x;
//				int globalY = (chunkY<<4)+y;
//				Entity e = w.getEntity(globalX, globalY);
//				if (e != null)
//					e.render(c, g, w);
				
				
			}
		}
	}
	
	public void update(GameContainer c, int delta, World w) {
		for (int y = 0; y < 16; y++) {
			for (int x = 0; x < 16; x++) {
				
				int i = getLocalLocationIndex(x, y);
				
				tiles.get(i).update(c, delta, w);
				Piece p = pieces.get(i);
				if (p != null)
					p.update(c, delta, w);
				
//				int globalX = (chunkX<<4)+x;
//				int globalY = (chunkY<<4)+y;
//				Entity e = w.getEntity(globalX, globalY);
//				if (e != null)
//					e.update(c, delta, w);
				
				
			}
		}
	}
	
	public void setPieceID(int x, int y, int id) {
		pieces.put(getGlobalLocationIndex(x, y), new Piece(x, y, id));
	}
	
	public void setPieceIDAndMetadata(int x, int y, int id, int metadata) {
		setPieceID(x, y, id);
		pieces.get(getGlobalLocationIndex(x, y)).metadata = metadata;
	}
	
	public Tile getTile(int x, int y) {
		return tiles.get(getGlobalLocationIndex(x, y));
	}
	
	public Piece getPiece(int x, int y) {
		return pieces.get(getGlobalLocationIndex(x, y));
	}
	
	public int getPieceID(int x, int y) {
		if (pieces.get(getGlobalLocationIndex(x, y)) == null) {
			return 0;
		}  else {
			return pieces.get(getGlobalLocationIndex(x, y)).getPieceID();
		}
	}
	
	public void setTileID(int x, int y, int id) {
		tiles.put(getGlobalLocationIndex(x, y), new Tile(x, y, id, 0, 113, 65));
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
