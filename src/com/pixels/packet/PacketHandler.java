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
//		Pixels.world.entities.put(Pixels.serverID, new EntityPlayer(packet.playerPosX, packet.playerPosY, false));
	}

	public static void handlePacketWorldData(PacketWorldData packet) {
		Pixels.world.chunks = packet.chunks;
		Pixels.world.entities = packet.entities;
		Pixels.world.entityPositions = packet.entityPositions;
		Pixels.world.setChunkLoadedRange(packet.minChunkX, packet.minChunkX, packet.maxChunkX, packet.maxChunkY);
		Pixels.client.addPacket(new PacketPlayerDidSpawn());
	}

	public static void handlePacketUpdateEntity(PacketUpdateEntity packet) {
		
		Pixels.world.updateEntityFromPacket(packet.serverID, packet.posX, packet.posY);
		
	}

	public static void handlePacketSpawnEntity(PacketSpawnEntity packet) {
		
		Entity e = Entity.getEntity(packet.entityID);
		e.posX = packet.posX;
		e.posY = packet.posY;
		Pixels.world.propogateEntity(e, packet.serverID);
	}

}
