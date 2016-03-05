package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;
import com.pixels.entity.Entity;

public class PacketUpdateEntity extends Packet {
	
	public PacketUpdateEntity() {
		this.id = 5;
	}
	
	public PacketUpdateEntity(Entity e) {
		this.id = 5;
		serverID = e.serverID;
		posX = e.posX;
		posY = e.posY;
	}
	
	// don't technically need the write data on client, client never uses this - player uses update player

	@Override
	public void writeData(CommunicationClient client) throws IOException {

		client.getOutput().writeInt(serverID);
		client.getOutput().writeFloat(posX);
		client.getOutput().writeFloat(posY);
		
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
				
		serverID = client.getInput().readInt();
		posX = client.getInput().readFloat();
		posY = client.getInput().readFloat();
				
		PacketHandler.handlePacketUpdateEntity(this);
		
	}
	
	int serverID;
	float posX, posY;

}
