package com.pixels.packet;

import com.pixels.entity.Entity;
import com.pixels.start.Pixels;
import com.pixels.world.World;

public class PacketHandler {
	
	public static void handlePacketLogin(PacketLogin packet) {
		Pixels.serverID = packet.serverID;
		System.out.println("login complete, serverID: " + Pixels.serverID);
	}

	public static void handlePacketSpawn(PacketSpawn packet) {
		Pixels.world = new World(packet.worldWidth, packet.worldHeight);
	}

	public static void handlePacketWorldData(PacketWorldData packet) {
		Pixels.world.chunks = packet.chunks;
		Pixels.world.entities = packet.entities;
		Pixels.world.entityPositionMap = packet.entityPositionMap;
		Pixels.world.setChunkLoadedRange(packet.minChunkX, packet.minChunkY, packet.maxChunkX, packet.maxChunkY);
		Pixels.client.addPacket(new PacketPlayerDidSpawn());
	}

	public static void handlePacketUpdateEntity(PacketUpdateEntity packet) {
		
		Pixels.world.getEntity(packet.serverID).setPosition(packet.posX, packet.posY);
				
	}

	public static void handlePacketSpawnEntity(PacketSpawnEntity packet) {
		
		Entity e = Entity.getEntity(packet.entityID);
		e.posX = packet.posX;
		e.posY = packet.posY;
		e.positionKey = packet.positionKey;
		Pixels.world.propogateEntity(e, packet.serverID);
	}

	public static void handlePacketUpdateWorld(PacketUpdateWorld packet) {
		
		//add new chunks
		for (Integer key : packet.chunks.keySet()) {
			// shoudn't overwrite anything if its working right
			Pixels.world.chunks.put(key, packet.chunks.get(key));
		}
		for (Integer key : packet.entities.keySet()) {
			// shoudn't overwrite anything if its working right
			Pixels.world.entities.put(key, packet.entities.get(key));
		}
		for (Integer key : packet.entityPositionMap.keySet()) {
			// shoudn't overwrite anything if its working right
			Pixels.world.entityPositionMap.put(key, packet.entityPositionMap.get(key));
		}
		
		//remove old ones
		Pixels.world.setChunkLoadedRange(packet.minChunkXLoaded, packet.minChunkYLoaded, packet.maxChunkXLoaded, packet.maxChunkYLoaded);
		Pixels.world.trimUnloadedChunks();
		
		Pixels.world.worldUpdateComplete();
		
	}

}
