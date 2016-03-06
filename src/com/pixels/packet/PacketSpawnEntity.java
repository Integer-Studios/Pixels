package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;
import com.pixels.entity.Entity;

public class PacketSpawnEntity extends Packet {
	
	public PacketSpawnEntity() {
		this.id = 6;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		
		int serverID = client.getInput().readInt();
		int entityID = client.getInput().readInt();
		int positionKey = client.getInput().readInt();
		float posX = client.getInput().readFloat();
		float posY = client.getInput().readFloat();
		
		entity = Entity.getEntity(entityID);
		entity.construct(serverID, positionKey, posX, posY);
		entity.readEntityData(client);
				
		PacketHandler.handlePacketSpawnEntity(this);
		
	}
	
	public Entity entity;

}
