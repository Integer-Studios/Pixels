package com.pixels.world;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.entity.Entity;
import com.pixels.entity.EntityPlayer;
import com.pixels.packet.PacketUpdateEntity;
import com.pixels.packet.PacketUpdatePlayer;
import com.pixels.packet.PacketUpdateWorld;
import com.pixels.start.Pixels;

public class World {
	
	public World(int w, int h) {
		
		chunkWidth = w;
		chunkHeight = h;
		
	}

	public void render(GameContainer c, Graphics g) {
		
		updateGlobalOffset();
		
		for (Chunk chunk : chunks.values()) {
			chunk.render(c, g, this);
		}
		
		for (Entity entity : entities.values()) {
			entity.render(c, g, this);
		}
	}
	
	public void update(GameContainer c, int delta) {

		for (Chunk chunk : chunks.values()) {
			chunk.update(c, delta, this);
		}
		
		for (Entity entity : entities.values()) {
			entity.update(c, delta, this);
		}
		
		checkShouldUpdateWorld();
		
	}
	
	private void checkShouldUpdateWorld() {
		
		Chunk c = getChunk(getPlayer());
		if (c.chunkX == minChunkXLoaded || c.chunkX == maxChunkXLoaded || c.chunkY == minChunkYLoaded || c.chunkY == maxChunkYLoaded) {
			if (!hasRequestedWorldUpdate) {
				Pixels.client.addPacket(new PacketUpdateWorld());
				hasRequestedWorldUpdate = true;
			}
		}
	}
	
	public void worldUpdateComplete() {
		hasRequestedWorldUpdate = false;
	}
	
	public EntityPlayer getPlayer() {
		return (EntityPlayer) entities.get(Pixels.serverID);
	}

	public void setPieceID(int x, int y, int id) {
		getChunk(x, y).setPieceID(x, y, id);
	}
	
	public int getPieceID(int x, int y) {
		return getChunk(x, y).getPieceID(x, y);
	}
	
//	all entities should come from server, so no need to prop this way
//	public int propogateEntity(Entity entity) {
//		return propogateEntity(entity, entities.size());
//	}
	
	public int propogateEntity(Entity e, int id) {
		entities.put(id, e);
		addEntityToPositionMap(e);
		return id;
	}
	
	public void addEntityToPositionMap(Entity e) {
		int key = getLocationIndex(e);
		e.positionKey = key;
		ArrayList<Integer> entities = entityPositionMap.get(key);
		if (entities == null) {
			entities = new ArrayList<Integer>();
		}
		entities.add(e.serverID);
		entityPositionMap.put(key, entities);
	}
	
	public void removeEntityFromPositionMap(Entity e) {
		int key = e.positionKey;
		ArrayList<Integer> entityMap = entityPositionMap.get(key);
		if (entityMap != null) {
			int i = entityMap.lastIndexOf(e.serverID);
			entityMap.remove(i);
		}
		entityPositionMap.put(key, entityMap);
	}
	
	public void removeEntityFromPositionMap(int id) {
		Entity e = entities.get(id);
		int key = e.positionKey;
		ArrayList<Integer> entities = entityPositionMap.get(key);
		if (entities != null) {
			entities.remove(e.serverID);
		}
		entityPositionMap.put(key, entities);
	}
	
	public void updateEntityPositionMap(Entity e) {
		if (e.positionKey != getLocationIndex(e)) {
			removeEntityFromPositionMap(e);
			addEntityToPositionMap(e);
		}
	}
	
	public void despawnEntity(Entity e) {
		removeEntityFromPositionMap(e);
		entities.remove(e.serverID);
	}
	
	public void despawnEntity(int id) {
		removeEntityFromPositionMap(id);
		entities.remove(id);
	}
	
//	public Entity getEntity(int x, int y) {		
//		Integer serverID = entityPositions.get(getLocationIndex(x, y));
//		if (serverID != null)
//			return entities.get(serverID);
//		else
//			return null;
//	}
	
	public Entity getEntity(int entityID) {
		return entities.get(entityID);
	}
//	entities should handle this at the end of their tick
//	public void moveEntity(int id, float x, float y) {
//				
//		Entity e = getEntity(id);
//		
//		if (e.posX == x && e.posY == y)
//			return;
//		
//		entityPositions.remove(getLocationIndex(e));
//		
//		e.posX = x;
//		e.posY = y;
//		
//		entityPositions.put(getLocationIndex(e), id);
//		
//		
//		if (e instanceof EntityPlayer)
//			Pixels.client.addPacket(new PacketUpdatePlayer((EntityPlayer)e));
//		else
//			Pixels.client.addPacket(new PacketUpdateEntity(e));
//		
//	}
//	
//	public void updateEntityFromPacket(int id, float x, float y) {
//
//		Entity e = getEntity(id);
//				
//		if (x == e.posX && y == e.posY)
//			return;
//		
//		entityPositions.remove(getLocationIndex(e));
//		
//		e.posX = x;
//		e.posY = y;
//		
//		entityPositions.put(getLocationIndex(e), id);
//				
//	}
	
	public Chunk getChunk(Entity e) {
		return chunks.get(getChunkIndex(Math.round(e.posX)>>4, Math.round(e.posY)>>4));
	}
	
	public Chunk getChunk(int x, int y) {
		return chunks.get(getChunkIndex(x>>4, y>>4));
	}
	
	public void setChunkLoadedRange(int x1, int y1, int x2, int y2) {
		minChunkXLoaded = x1;
		minChunkYLoaded = y1;
		maxChunkXLoaded = x2;
		maxChunkYLoaded = y2;
	}
	
	public void trimUnloadedChunks() {
		
//		this got fucked from floating point
		
		for (Integer index : chunks.keySet()) {
			Chunk c = chunks.get(index);
			if (c.chunkX < minChunkXLoaded || c.chunkX > maxChunkXLoaded || c.chunkY < minChunkYLoaded || c.chunkY > maxChunkYLoaded) {
				chunks.remove(index);
				
				// remove entities in chunk
				for (int y = (c.chunkY-1)<<4; y < c.chunkY<<4; y++) {
					for (int x = (c.chunkX-1)<<4; x < c.chunkX<<4; x++) {
						ArrayList<Integer> indexes = entityPositionMap.get(getLocationIndex(x, y));
						if (indexes != null) {
							for (int a = 0; a < indexes.size(); a++) {
								int i = indexes.get(a);
								//remove entity
								entities.remove(i);
								entityPositionMap.remove(getLocationIndex(x, y));
							}
						}
					}
				}
			}
		}
		
	}
	
	private int getChunkIndex(int chunkX, int chunkY) {
		return chunkY*chunkWidth + chunkX;
	}
	
	public int getLocationIndex(int x, int y) {
		return y*(chunkWidth<<4) + x;
	}
	
	public int getLocationIndex(Entity e) {
		return Math.round(e.posY)*(chunkWidth<<4) + Math.round(e.posX);
	}
	
	private void updateGlobalOffset() {
		Entity player = entities.get(Pixels.serverID);
		globalOffsetX = (int)(Display.getWidth()/2)-(int)(player.posX * tileConstant);
		globalOffsetY = (int)(Display.getHeight()/2)-(int)(player.posY * tileConstant);
	}
	
	public int chunkWidth, chunkHeight;
	public ConcurrentHashMap<Integer,Chunk> chunks = new ConcurrentHashMap<Integer,Chunk>();
	public ConcurrentHashMap<Integer,Entity> entities = new ConcurrentHashMap<Integer,Entity>();
	public ConcurrentHashMap<Integer,ArrayList<Integer>> entityPositionMap = new ConcurrentHashMap<Integer,ArrayList<Integer>>();

	//	public ConcurrentHashMap<Integer,Integer> entityPositions = new ConcurrentHashMap<Integer,Integer>();
	
	public int maxChunkXLoaded, maxChunkYLoaded;
	public int minChunkXLoaded, minChunkYLoaded;

	public int tileConstant = 30;
	public int globalOffsetX = 0;
	public int globalOffsetY = 0;
	public boolean isLoaded = false;
	public boolean hasRequestedWorldUpdate = false;

}
