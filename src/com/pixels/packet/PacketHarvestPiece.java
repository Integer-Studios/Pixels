package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;
import com.pixels.piece.Piece;

public class PacketHarvestPiece extends Packet {
	
	public PacketHarvestPiece(Piece p) {
		this.id = 14;
		x = p.posX;
		y = p.posY;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		client.getOutput().writeInt(x);
		client.getOutput().writeInt(y);
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	int x, y;

}
