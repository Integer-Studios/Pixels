package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;

public class PacketLogin extends Packet {
	
	public PacketLogin() {
		this.id = 1;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		// user ID sent in all packets
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		serverID = client.getInput().readInt();
		PacketHandler.handlePacketLogin(this);
	}
	
	public int serverID;

}
