package com.pixels.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.entity.Entity;
import com.pixels.entity.EntityPlayer;
import com.pixels.packet.PacketUpdateWorld;
import com.pixels.piece.Piece;
import com.pixels.start.Pixels;

public class World {
	
	public World(int w, int h) {
		
		chunkWidth = w;
		chunkHeight = h;
		
		entities = new EntityRegister();
		
	}

	public void render(GameContainer c, Graphics g) {

		updateGlobalOffset();
			
		if (shouldTrim) {
			trimUnloadedChunks();
			shouldTrim = false;
		}

		for (int chunkY = minChunkYLoaded; chunkY <= maxChunkYLoaded; chunkY++) {
			
			HashMap<Integer, ArrayList<Piece>> pieces = new HashMap<Integer, ArrayList<Piece>>();
			for (int chunkX = minChunkXLoaded; chunkX <= maxChunkXLoaded; chunkX++) {
				Chunk chunk = getChunk(chunkX, chunkY);
				
				ArrayList<Piece> p = new ArrayList<Piece>();
				
				for (int y = 0 ; y < 16; y++) {
					
					
					p = new ArrayList<Piece>();
					
					for (int x = 0 ; x < 16; x++) {
						chunk.getTile((chunkX<<4)+x, (chunkY<<4)+y).render(c, g, this);
						p.add(chunk.getPiece((chunkX<<4)+x, (chunkY<<4)+y));
					}
					
					
					ArrayList<Piece> pieceYGroup = pieces.get(y);


					if (pieceYGroup == null) {
						pieces.put(y, p);
					} else {
						pieceYGroup.addAll(p);
						pieces.put(y, pieceYGroup);
					}
					
				}
				
			}
			
			for (int y = 0; y < 16; y++) {

				entities.renderYGroup(c, g, this, (chunkY<<4) + y);
				
				for (Piece piece : pieces.get(y)) {
					
					if (piece != null) {
						piece.render(c, g, this);
					}
				}
				
			}

		}

	}
	
	public void update(GameContainer c, int delta) {

		for (Chunk chunk : chunks.values()) {
			chunk.update(c, delta, this);
		}
		
		entities.update(c, delta, this);
		
		checkShouldUpdateWorld();
		
	}
	
	private void checkShouldUpdateWorld() {

		// update world doesnt work and render needs to be written
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
		getChunkFromTileCoordinates(x, y).setPieceID(x, y, id);
	}
	
	public int getPieceID(int x, int y) {
		return getChunkFromTileCoordinates(x, y).getPieceID(x, y);
	}

	public void propogateEntity(Entity e) {
		entities.add(e);
	}

	public void despawnEntity(Entity e) {
		entities.remove(e);
	}
	
	public void despawnEntity(int id) {
		entities.remove(id);
	}
		
	public Entity getEntity(int entityID) {
		return entities.get(entityID);
	}
	
	public void addChunk(Chunk chunk) {
		// TODO Auto-generated method stub
		chunks.put(getChunkIndex(chunk.chunkX, chunk.chunkY), chunk);
	}
	
	public Chunk getChunk(Entity e) {
		return chunks.get(getChunkIndex(Math.round(e.posX)>>4, Math.round(e.posY)>>4));
	}
	
	public Chunk getChunk(int x, int y) {
		return chunks.get(getChunkIndex(x, y));
	}
	
	public Chunk getChunkFromTileCoordinates(int x, int y) {
		return chunks.get(getChunkIndex(x>>4, y>>4));
	}
	
	public void setChunkLoadedRange(int x1, int y1, int x2, int y2) {
		minChunkXLoaded = x1;
		minChunkYLoaded = y1;
		maxChunkXLoaded = x2;
		maxChunkYLoaded = y2;
		shouldTrim = true;
	}
		
	public void trimUnloadedChunks() {
				
		for (Integer index : chunks.keySet()) {
			Chunk c = chunks.get(index);
			if (c.chunkX < minChunkXLoaded || c.chunkX > maxChunkXLoaded || c.chunkY < minChunkYLoaded || c.chunkY > maxChunkYLoaded) {
				chunks.remove(index);
				// remove entities in chunk
				for (int y = c.chunkY<<4; y < (c.chunkY+1)<<4; y++) {
					for (int x = c.chunkX<<4; x < (c.chunkX+1)<<4; x++) {
						ArrayList<Integer> indexesList = entities.getIDs(x, y);
						if (indexesList != null) {
							Object[] indexes = indexesList.toArray();
							for (int a = 0; a < indexes.length; a++) {
								int i = (int) indexes[a];
								entities.remove(i);
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
		Entity player = getPlayer();
		globalOffsetX = (int)(Display.getWidth()/2)-(int)(player.posX * tileConstant);
		globalOffsetY = (int)(Display.getHeight()/2)-(int)(player.posY * tileConstant);
	}
	
	public int chunkWidth, chunkHeight;
	public ConcurrentHashMap<Integer,Chunk> chunks = new ConcurrentHashMap<Integer,Chunk>();
	public EntityRegister entities;

	public int maxChunkXLoaded, maxChunkYLoaded;
	public int minChunkXLoaded, minChunkYLoaded;

	public int tileConstant = 40;
	public int globalOffsetX = 0;
	public int globalOffsetY = 0;
	public boolean isLoaded = false;
	public boolean hasRequestedWorldUpdate = false;
	public boolean shouldTrim = false;

}
