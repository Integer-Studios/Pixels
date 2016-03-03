package com.pixels.packet;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import com.pixels.communication.CommunicationClient;
import com.pixels.entity.Entity;
import com.pixels.piece.Piece;
import com.pixels.start.Pixels;
import com.pixels.tile.Tile;
import com.pixels.world.Chunk;

public class PacketWorldData extends Packet {
	
	public PacketWorldData() {
		this.id = 3;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		// TODO Auto-generated method stub
		readChunks(client);
		readEntities(client);
		
		PacketHandler.handlePacketWorldData(this);
		
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
		
		int chunkX = client.getInput().readInt();
		int chunkY = client.getInput().readInt();
		
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
		
		chunks.put(getLocationIndex(chunkX, chunkY, Pixels.world.chunkWidth), chunk);
	}
	
	public void readTile(int key, int x, int y, Chunk chunk, CommunicationClient client) throws IOException {
		int id = client.getInput().readInt();
		chunk.tiles.put(key, new Tile(((chunk.chunkX << 4) + x), ((chunk.chunkY << 4) + y), id));
	}
	
	public void readPiece(int key, int x, int y, Chunk chunk, CommunicationClient client) throws IOException {
		int id = client.getInput().readInt();
		if (id != 0)
			chunk.pieces.put(key, new Piece(((chunk.chunkX << 4) + x), ((chunk.chunkY << 4) + y), id));
	}
	
	public void readEntity(CommunicationClient client) throws IOException {
		int serverID = client.getInput().readInt();
		//need to work on this for entities
		int entityID = client.getInput().readInt();
		int posX = client.getInput().readInt();
		int posY = client.getInput().readInt();
		Entity e = Entity.getEntity(entityID);
		e.initializePosition(posX,  posY);
		e.readEntityData(client);
		entities.put(serverID, e);
		entityPositions.put(getLocationIndex(posX,  posY, (Pixels.world.chunkWidth << 4)), serverID);
	}
	
	private int getLocationIndex(int x, int y, int width) {
		return y*width + x;
	}
	
	public ConcurrentHashMap<Integer,Chunk> chunks = new ConcurrentHashMap<Integer,Chunk>();
	public ConcurrentHashMap<Integer,Entity> entities = new ConcurrentHashMap<Integer,Entity>();
	public ConcurrentHashMap<Integer,Integer> entityPositions = new ConcurrentHashMap<Integer,Integer>();

}
