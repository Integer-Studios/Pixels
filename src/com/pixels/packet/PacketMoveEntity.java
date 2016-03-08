package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;
import com.pixels.entity.Entity;

public class PacketMoveEntity extends Packet {
	
	public PacketMoveEntity() {
		this.id = 10;
	}
	
	public PacketMoveEntity(Entity e) {
		this.id = 10;
		serverID = e.serverID;
		posX = e.posX;
		posY = e.posY;
		velocityX = e.velocityX;
		velocityY = e.velocityY;
	}
	
	// don't technically need the write data on client, client never uses this - player uses update player

	@Override
	public void writeData(CommunicationClient client) throws IOException {

		client.getOutput().writeInt(serverID);
		client.getOutput().writeFloat(posX);
		client.getOutput().writeFloat(posY);
		client.getOutput().writeFloat(velocityX);
		client.getOutput().writeFloat(velocityY);
//		System.out.println("WRITE MOVE " + serverID + " " + posX + " " + posY + " " + velocityX + " " + velocityY);
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {

		serverID = client.getInput().readInt();
		posX = client.getInput().readFloat();
		posY = client.getInput().readFloat();
		velocityX = client.getInput().readFloat();
		velocityY = client.getInput().readFloat();
//		System.out.println("READ MOVE " + serverID + " " + posX + " " + posY + " " + velocityX + " " + velocityY);

		PacketHandler.handlePacketMoveEntity(this);
		
	}
	
	int serverID;
	float posX, posY, velocityX, velocityY;

}
