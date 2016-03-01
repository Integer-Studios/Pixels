package com.pixels.world;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.entity.Entity;

public class World {
	
	public World(int w, int h) {
		
		chunkWidth = w;
		chunkHeight = h;
		generateWorld();
		
	}
	
	public void generateWorld() {
		
		for (int chunkY = 0; chunkY < chunkHeight; chunkY++) {
			for (int chunkX = 0; chunkX < chunkWidth; chunkX++) {
				chunks.add(new Chunk(chunkX, chunkY));
			}
		}

	}
	
	public void render(GameContainer c, Graphics g) {
		for (int i = 0; i < chunks.size(); i++) {
			chunks.get(i).render(c, g, this);
		}
	}
	
	public void update(GameContainer c, int delta) {
		for (int i = 0; i < chunks.size(); i++) {
			chunks.get(i).update(c, delta, this);
		}
	}
	
	public void setPiece(int x, int y, int id) {
		chunks.get(getChunkIndex(x>>4, y>>4)).setPiece(x, y, id);
	}
	
	public int getPieceID(int x, int y) {
		return chunks.get(getChunkIndex(x>>4, y>>4)).getPieceID(x, y);
	}
	
	private int getChunkIndex(int chunkX, int chunkY) {
		return chunkY*chunkWidth + chunkX;
	}
	
	public int chunkWidth, chunkHeight;
	public ArrayList<Chunk> chunks = new ArrayList<Chunk>();
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public int tileConstant = 30;
	public int globalOffsetX = 0;
	public int globalOffsetY = 0;
}
