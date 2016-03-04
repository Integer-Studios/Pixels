package com.pixels.world;

import java.util.concurrent.ConcurrentHashMap;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.entity.Entity;
import com.pixels.entity.EntityPlayer;
import com.pixels.packet.PacketUpdateEntity;
import com.pixels.packet.PacketUpdatePlayer;
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
	}
	
	public void update(GameContainer c, int delta) {

		for (Chunk chunk : chunks.values()) {
			chunk.update(c, delta, this);
		}
		
		checkShouldUpdateWorld();
		
	}
	
	private void checkShouldUpdateWorld() {
		
//		Chunk c = getChunk(getPlayer());
//		
//		if (c.chunkX == minChunkXLoaded || c.chunkX == maxChunkXLoaded || c.chunkY == minChunkYLoaded || c.chunkY == maxChunkYLoaded) {
//			Pixels.client.addPacket(new PacketUpdateWorld());
//		}
//		
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
	
	public int propogateEntity(Entity entity) {
		return propogateEntity(entity, entities.size());
	}
	
	public int propogateEntity(Entity entity, int id) {
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
	
	public void moveEntity(int id, int x, int y) {
				
		Entity e = getEntity(id);
		
		if (e.posX == x && e.posY == y)
			return;
		
		entityPositions.remove(getLocationIndex(e.posX, e.posY));
		
		e.posX = x;
		e.posY = y;
		
		entityPositions.put(getLocationIndex(e.posX, e.posY), id);
		
		
		if (e instanceof EntityPlayer)
			Pixels.client.addPacket(new PacketUpdatePlayer((EntityPlayer)e));
		else
			Pixels.client.addPacket(new PacketUpdateEntity(e));
		
	}
	
	public void updateEntityFromPacket(int id, int x, int y) {

		Entity e = getEntity(id);
				
		if (x == e.posX && y == e.posY)
			return;
		
		entityPositions.remove(getLocationIndex(e.posX, e.posY));
		
		e.posX = x;
		e.posY = y;
		
		entityPositions.put(getLocationIndex(e.posX, e.posY), id);
				
	}
	
	public Chunk getChunk(Entity e) {
		return chunks.get(getChunkIndex(e.posX>>4, e.posY>>4));
	}
	
	public Chunk getChunk(int x, int y) {
		return chunks.get(getChunkIndex(x>>4, y>>4));
	}
	
	public void setChunkLoadedRange(int x1, int y1, int x2, int y2) {
		minChunkXLoaded = x1;
		maxChunkXLoaded = y1;
		minChunkYLoaded = x2;
		maxChunkYLoaded = y2;
	}
	
	private int getChunkIndex(int chunkX, int chunkY) {
		return chunkY*chunkWidth + chunkX;
	}
	
	public int getLocationIndex(int x, int y) {
		return y*(chunkWidth<<4) + x;
	}
	
	public int getLocationIndex(Entity e) {
		return e.posY*(chunkWidth<<4) + e.posX;
	}
	
	private void updateGlobalOffset() {
		Entity player = entities.get(Pixels.serverID);
		globalOffsetX = (int)(Display.getWidth()/2)-(int)(player.posX * tileConstant);
		globalOffsetY = (int)(Display.getHeight()/2)-(int)(player.posY * tileConstant);
	}
	
	public int chunkWidth, chunkHeight;
	public ConcurrentHashMap<Integer,Chunk> chunks = new ConcurrentHashMap<Integer,Chunk>();
	public ConcurrentHashMap<Integer,Entity> entities = new ConcurrentHashMap<Integer,Entity>();
	public ConcurrentHashMap<Integer,Integer> entityPositions = new ConcurrentHashMap<Integer,Integer>();
	
	public int maxChunkXLoaded, maxChunkYLoaded;
	public int minChunkXLoaded, minChunkYLoaded;

	public int tileConstant = 40;
	public int globalOffsetX = 0;
	public int globalOffsetY = 0;
	public boolean isLoaded = false;

}
