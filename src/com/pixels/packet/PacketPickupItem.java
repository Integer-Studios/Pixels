package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;

public class PacketPickupItem extends Packet{
	
	public PacketPickupItem() {
		this.id = 13;
	}

	@Override
	public void writeData(CommunicationClient servlet) throws IOException {
		
	}

	@Override
	public void readData(CommunicationClient servlet) throws IOException {
		
		itemID = servlet.getInput().readInt();
		PacketHandler.handlePacketPickupItem(this);
		
	}
	
	int itemID;

}
