package com.pixels.packet;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import com.pixels.communication.CommunicationClient;
import com.pixels.entity.Entity;
import com.pixels.piece.Piece;
import com.pixels.start.Pixels;
import com.pixels.tile.Tile;
import com.pixels.world.Chunk;
import com.pixels.world.EntityRegister;

public class PacketUpdateWorld extends Packet {
	
	public PacketUpdateWorld() {
		this.id = 8;
		minChunkXLoaded = Pixels.world.minChunkXLoaded;
		minChunkYLoaded = Pixels.world.minChunkYLoaded;
		maxChunkXLoaded = Pixels.world.maxChunkXLoaded;
		maxChunkYLoaded = Pixels.world.maxChunkYLoaded;
		percievedPosX = Pixels.world.getPlayer().posX;
		percievedPosY = Pixels.world.getPlayer().posY;
		entities = new EntityRegister();
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		
		client.getOutput().writeInt(minChunkXLoaded);
		client.getOutput().writeInt(minChunkYLoaded);
		client.getOutput().writeInt(maxChunkXLoaded);
		client.getOutput().writeInt(maxChunkYLoaded);
		
		client.getOutput().writeFloat(percievedPosX);
		client.getOutput().writeFloat(percievedPosY);
		
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		
		readChunks(client);
		readEntities(client);
				
		minChunkXLoaded = client.getInput().readInt();	
		minChunkYLoaded = client.getInput().readInt();		
		maxChunkXLoaded = client.getInput().readInt();		
		maxChunkYLoaded = client.getInput().readInt();	

		PacketHandler.handlePacketUpdateWorld(this);
		
	}
	
	public void readChunks(CommunicationClient client) throws IOException {
				
		int chunks = client.getInput().readInt();
		
		for (int i = 0; i < chunks; i++) {
			
			readChunk(client);
			
		}
		
	}
	
	public void readEntities(CommunicationClient client) throws IOException {
		
		int entities = client.getInput().readInt();
				
		for (int i = 0; i < entities; i++) {
			
			readEntity(client);
			
		}
	}
	
	public void readChunk(CommunicationClient client) throws IOException {
		
		// read chunk coordinates
		int chunkX = client.getInput().readInt();
		int chunkY = client.getInput().readInt();
				
		// build chunk from tile and piece data
		Chunk chunk = new Chunk(chunkX, chunkY);
		
		int x = 0;
		int y = 0;
		for (int i = 0; i < 256; i++) {
			
			readTile(i, x, y, chunk, client);
			readPiece(i, x, y, chunk, client);
			
			x++;
			if ((i+1) % 16 == 0) {
				y++;
				x = 0;
			}
		}
		
		//add chunk to chunks
		chunks.put(getLocationIndex(chunkX, chunkY, Pixels.world.chunkWidth), chunk);
	}
	
	public void readTile(int key, int x, int y, Chunk chunk, CommunicationClient client) throws IOException {
		int id = client.getInput().readInt();
		int elevation = client.getInput().readInt();
		int humidity = client.getInput().readInt();
		int tempurature = client.getInput().readInt();
		chunk.tiles.put(key, new Tile(((chunk.chunkX << 4) + x), ((chunk.chunkY << 4) + y), id, elevation, humidity, tempurature));
	}
	
	public void readPiece(int key, int x, int y, Chunk chunk, CommunicationClient client) throws IOException {
		int id = client.getInput().readInt();
		if (id != 0)
			chunk.pieces.put(key, new Piece(((chunk.chunkX << 4) + x), ((chunk.chunkY << 4) + y), id));
	}
	
	public void readEntity(CommunicationClient client) throws IOException {

		// read entity data
		int serverID = client.getInput().readInt();
		int entityID = client.getInput().readInt();
		int positionKey = client.getInput().readInt();
		float posX = client.getInput().readFloat();
		float posY = client.getInput().readFloat();
		
		//if the online player entity is you, change to entityplayer
//		if (serverID != Pixels.serverID) {
			//build entity without constructor
			Entity e = Entity.getEntity(entityID);
			e.construct(serverID, positionKey, posX, posY);
			e.readEntityData(client);
			
			//add entity to world without propogation
			entities.add(e);
//		}
		
	}
	
	private int getLocationIndex(int x, int y, int width) {
		return y*width + x;
	}
	
	public ConcurrentHashMap<Integer,Chunk> chunks = new ConcurrentHashMap<Integer,Chunk>();
	public EntityRegister entities;
	public int minChunkXLoaded, minChunkYLoaded, maxChunkXLoaded, maxChunkYLoaded;
	public float percievedPosX, percievedPosY;
}
