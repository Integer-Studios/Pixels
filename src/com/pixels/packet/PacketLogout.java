package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;
import com.pixels.start.Pixels;

public class PacketLogout extends Packet {

	public int userID, serverID;
	
	public PacketLogout() {
		
		this.id = 9;
		
	}
	
	public PacketLogout(int userID) {
		this.id = 9;
		this.userID = userID;
	}
	
	@Override
	public void writeData(CommunicationClient client) throws IOException {

		client.getOutput().writeInt(this.userID);
		
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		
		this.userID = client.getInput().readInt();
		this.serverID = client.getInput().readInt();
		System.out.println("Received logout from server: " + userID + " " + serverID);

		Pixels.world.entityPositions.remove(Pixels.world.getLocationIndex(Pixels.world.entities.get(this.serverID)));
		Pixels.world.entities.remove(this.serverID);
		
	}

}
