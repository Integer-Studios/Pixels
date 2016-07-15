package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;
import com.pixels.piece.Piece;

public class PacketUpdatePiece extends Packet {
	
	public PacketUpdatePiece() {
		this.id = 11;
	}
	
	public PacketUpdatePiece(Piece p) {
		this.id = 11;
		posX = p.posX;
		posY = p.posY;
		pieceID = p.id;
		metadata = p.metadata;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		client.getOutput().writeInt(posX);
		client.getOutput().writeInt(posY);
		client.getOutput().writeInt(pieceID);
		client.getOutput().writeInt(metadata);
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		posX = client.getInput().readInt();
		posY = client.getInput().readInt();
		pieceID = client.getInput().readInt();
		metadata = client.getInput().readInt();
		
		PacketHandler.handlePacketUpdatePiece(this);
		
	}
	
	public int posX, posY, pieceID, metadata;

}
