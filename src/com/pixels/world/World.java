package com.pixels.world;

import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.entity.Entity;

public class World {
	
	public World(int w, int h) {
		
		chunkWidth = w;
		chunkHeight = h;
		
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
	
	public void setPieceID(int x, int y, int id) {
		getChunk(x, y).setPieceID(x, y, id);
	}
	
	public int getPieceID(int x, int y) {
		return getChunk(x, y).getPieceID(x, y);
	}
	
	public int propogateEntity(Entity entity) {
		int id = entities.size();
		entities.put(id, entity);
		entityPositions.put(getLocationIndex(entity.posX, entity.posY), id);
		return id;
	}
	
	public Entity getEntity(int x, int y) {		
		Integer serverID = entityPositions.get(getLocationIndex(x, y));
		if (serverID != null)
			return entities.get(serverID);
		else
			return null;
	}
	
	public Entity getEntity(int entityID) {
		return entities.get(entityID);
	}
	
	public void moveEntity(int id, int x1, int y1, int x2, int y2) {
		entityPositions.remove(getLocationIndex(x1, y1));
		entityPositions.put(getLocationIndex(x2, y2), id);
	}
	
	public Chunk getChunk(int x, int y) {
		return chunks.get(getChunkIndex(x>>4, y>>4));
	}
	
	private int getChunkIndex(int chunkX, int chunkY) {
		return chunkY*chunkWidth + chunkX;
	}
	
	private int getLocationIndex(int x, int y) {
		return y*(chunkWidth<<4) + x;
	}
	
	public int chunkWidth, chunkHeight;
	public ConcurrentHashMap<Integer,Chunk> chunks = new ConcurrentHashMap<Integer,Chunk>();
	public ConcurrentHashMap<Integer,Entity> entities = new ConcurrentHashMap<Integer,Entity>();
	public ConcurrentHashMap<Integer,Integer> entityPositions = new ConcurrentHashMap<Integer,Integer>();

	public int tileConstant = 30;
	public int globalOffsetX = 0;
	public int globalOffsetY = 0;
	public boolean isLoaded = false;

}
