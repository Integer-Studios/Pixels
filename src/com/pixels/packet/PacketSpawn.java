package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;

public class PacketSpawn extends Packet {
	
	public PacketSpawn() {
		this.id = 2;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {

		worldWidth = client.getInput().readInt();
		worldHeight = client.getInput().readInt();
		playerPosX = client.getInput().readFloat();
		playerPosY = client.getInput().readFloat();
		
		PacketHandler.handlePacketSpawn(this);
		
	}
	
	public int worldWidth, worldHeight;
	public float playerPosX, playerPosY;


}
