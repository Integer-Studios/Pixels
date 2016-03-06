package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;
import com.pixels.entity.EntityPlayer;
import com.pixels.start.Pixels;

public class PacketUpdatePlayer extends Packet {

	public PacketUpdatePlayer() {
		this.id = 7;
	}
	
	public PacketUpdatePlayer(EntityPlayer e) {
		this.id = 7;
		serverID = Pixels.serverID;
		posX = e.posX;
		posY = e.posY;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		
		client.getOutput().writeInt(serverID);
		client.getOutput().writeFloat(posX);
		client.getOutput().writeFloat(posY);
		
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		
	}
	
	public int serverID;
	public float posX, posY;

}
