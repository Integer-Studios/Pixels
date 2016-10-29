package com.pixels.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.entity.Entity;
import com.pixels.entity.EntityPlayer;
import com.pixels.input.InterfaceManager;
import com.pixels.input.KeyBinder;
import com.pixels.input.KeyBinding;
import com.pixels.input.KeyCode;
import com.pixels.packet.PacketUpdateWorld;
import com.pixels.piece.Piece;
import com.pixels.start.Pixels;
import com.pixels.tile.Tile;
import com.pixels.util.CollisionManager;
import com.pixels.util.TextureLoader;

public class World implements KeyBinder {
	
	public World(int w, int h) {
		
		chunkWidth = w;
		chunkHeight = h;
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("in", KeyCode.KEY_EQUALS, this));
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("out", KeyCode.KEY_MINUS, this));
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
		
		if (zoomIn) {
			tileConstant ++;
		} else if (zoomOut && tileConstant > 8) {
			tileConstant --;
		}
//		else {
//			
//			int e = getElevation((int)getPlayer().posX, (int)getPlayer().posY);
//			e = 65 - e*2;
//			if (e > 8 && (tileConstant - e > -10 || tileConstant - e < 10)) {
//				tileConstant = e;
//			}
//		}
		
		
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
	
	public void setElevation(int x, int y, int e) {
		getChunkFromTileCoordinates(x, y).getTile(x, y).elevation = e;
	}
	
	public int getElevation(int x, int y) {
		Chunk c = getChunkFromTileCoordinates(x, y);
		if (c == null)
			return 0;
		else
			return c.getTile(x, y).elevation;
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
	
	public void setPieceIDAndMetadata(int x, int y, int id, int metadata) {
		getChunkFromTileCoordinates(x, y).setPieceIDAndMetadata(x, y, id, metadata);
	}
	
	public int getPieceID(int x, int y) {
		return getChunkFromTileCoordinates(x, y).getPieceID(x, y);
	}
	
	public Piece getPiece(int x, int y) {
		return getChunkFromTileCoordinates(x, y).getPiece(x, y);
	}
	
	public void setTileID(int x, int y, int id) {
		getChunkFromTileCoordinates(x, y).setTileID(x, y, id);
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
	
	public void checkEntityCollisions(Entity e) {
		int radius = 1;
		
		for (int y = ((int)e.posY - radius); y <= ((int)e.posY + radius); y++) {
			for (int x = ((int)e.posX - radius);x <= ((int)e.posX + radius); x++) {
				//check collision for piece here
				Piece p = getPiece(x, y);
				if (p != null)
					CollisionManager.testPieceCollision(e, p);
				//check collision for all entities here
				ArrayList<Entity> localEntities = entities.get(x, y);
				if (localEntities != null) {
					for (Entity entity : localEntities) {
						if (entity.serverID != e.serverID)
							CollisionManager.testEntityCollision(e, entity);
					}
				}
			}
		}

	}
	
	public void addChunk(Chunk chunk) {
		// TODO Auto-generated method stub
		chunks.put(getChunkIndex(chunk.chunkX, chunk.chunkY), chunk);
	}
	
	public Chunk getChunk(Entity e) {
		return chunks.get(getChunkIndex(((int)e.posX)>>4, ((int)e.posY)>>4));
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
		return ((int)e.posY)*(chunkWidth<<4) + ((int)e.posX);
	}
	
	private void updateGlobalOffset() {
		Entity player = getPlayer();
		globalOffsetX = (int)(Display.getWidth()/2)-(int)(player.posX * tileConstant);
		globalOffsetY = (int)(Display.getHeight()/2)-(int)(player.posY * tileConstant);
	}
	
	
	@Override
	public void onKeyDown(String name) {
		// TODO Auto-generated method stub
		if (name.equals("in")) {
			zoomIn = true;
		}
		if (name.equals("out")) {
			zoomOut = true;
		}
	}

	@Override
	public void onKeyUp(String name) {
		// TODO Auto-generated method stub
		if (name.equals("in")) {
			zoomIn = false;
		}
		if (name.equals("out")) {
			zoomOut = false;
		}
	}
	
	public int UIToWorldCoordX(int x) {
		return (int)(((float)(x - globalOffsetX) / (float)tileConstant));
	}
	
	public int UIToWorldCoordY(int y) {
		return (int)(((float)(y - globalOffsetY) / (float)tileConstant));
	}
	
	public int chunkWidth, chunkHeight;
	public ConcurrentHashMap<Integer,Chunk> chunks = new ConcurrentHashMap<Integer,Chunk>();
	public EntityRegister entities;

	public int maxChunkXLoaded, maxChunkYLoaded;
	public int minChunkXLoaded, minChunkYLoaded;
	
	public int tileConstant = 65;
	public int globalOffsetX = 0;
	public int globalOffsetY = 0;
	public boolean isLoaded = false;
	public boolean hasRequestedWorldUpdate = false;
	public boolean shouldTrim = false;
	public boolean zoomIn = false, zoomOut = false;
	
}
