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

	@Override
	public void writeData(CommunicationClient client) throws IOException {

		client.getOutput().writeInt(serverID);
		client.getOutput().writeInt(posX);
		client.getOutput().writeInt(posY);
		
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		
		serverID = client.getInput().readInt();
		posX = client.getInput().readInt();
		posY = client.getInput().readInt();
				
		PacketHandler.handlePacketUpdateEntity(this);
		
	}
	
	int serverID, posX, posY;

}
