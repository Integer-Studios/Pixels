package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;
import com.pixels.entity.Entity;

public class PacketSpawnEntity extends Packet {
	
	public PacketSpawnEntity() {
		this.id = 6;
	}
	
	public PacketSpawnEntity(Entity e) {
		this.id = 6;
		serverID = e.serverID;
		entityID = e.id;
		posX = e.posX;
		posY = e.posY;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		
		serverID = client.getInput().readInt();
		entityID = client.getInput().readInt();
		posX = client.getInput().readInt();
		posY = client.getInput().readInt();
		
		System.out.println("spawn entity: " + serverID);
		
		PacketHandler.handlePacketSpawnEntity(this);
		
	}
	
	int serverID, entityID, posX, posY;

}
