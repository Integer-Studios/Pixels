package com.pixels.packet;

import com.pixels.entity.Entity;
import com.pixels.start.Pixels;
import com.pixels.util.Log;
import com.pixels.util.ThreadName;
import com.pixels.world.World;

public class PacketHandler {
	
	public static void handlePacketLogin(PacketLogin packet) {
		Pixels.serverID = packet.serverID;
		Log.print(ThreadName.CLIENT, "login complete, serverID: " + Pixels.serverID);
	}

	public static void handlePacketSpawn(PacketSpawn packet) {
		Pixels.world = new World(packet.worldWidth, packet.worldHeight);
	}

	public static void handlePacketWorldData(PacketWorldData packet) {
		Pixels.world.chunks = packet.chunks;
		Pixels.world.entities = packet.entities;
		Pixels.world.setChunkLoadedRange(packet.minChunkX, packet.minChunkY, packet.maxChunkX, packet.maxChunkY);
		Pixels.didInitialize();
	}

	public static void handlePacketUpdateEntity(PacketUpdateEntity packet) {
//		shouldnt get entities that are unloaded in the future, but for now this works
		Entity e = Pixels.world.getEntity(packet.serverID);

		if (e != null) {
			e.setPosition(packet.posX, packet.posY);
			e.setVelocity(packet.velocityX, packet.velocityY);
		}
	}
	
	public static void handlePacketMoveEntity(PacketMoveEntity packet) {
//		shouldnt get entities that are unloaded in the future, but for now this works
		Entity e = Pixels.world.getEntity(packet.serverID);
		if (e != null) {
			e.setVelocity(packet.velocityX, packet.velocityY);
		}
				
	}

	public static void handlePacketSpawnEntity(PacketSpawnEntity packet) {
		
		Pixels.world.propogateEntity(packet.entity);
		
	}

	public static void handlePacketUpdateWorld(PacketUpdateWorld packet) {
		
		//add new chunks
		for (Integer index : packet.chunks.keySet()) {
			// shoudn't overwrite anything if its working right
			Pixels.world.addChunk(packet.chunks.get(index));
		}
		
		//add new entities
		Pixels.world.entities.replace(packet.entities);
		
		//remove old ones
		Pixels.world.setChunkLoadedRange(packet.minChunkXLoaded, packet.minChunkYLoaded, packet.maxChunkXLoaded, packet.maxChunkYLoaded);
		
		Pixels.world.worldUpdateComplete();
		
	}

	public static void handlePacketUpdatePiece(PacketUpdatePiece packet) {

		Pixels.world.setPieceIDAndMetadata(packet.posX, packet.posY, packet.pieceID, packet.metadata);
		
	}

}
