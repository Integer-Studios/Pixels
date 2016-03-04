package com.pixels.packet;

import java.io.IOException;

import com.pixels.communication.CommunicationClient;
import com.pixels.start.Pixels;

public class PacketUpdateWorld extends Packet {
	
	public PacketUpdateWorld() {
		this.id = 7;
		minChunkXLoaded = Pixels.world.minChunkXLoaded;
		minChunkYLoaded = Pixels.world.minChunkYLoaded;
		maxChunkXLoaded = Pixels.world.maxChunkXLoaded;
		maxChunkYLoaded = Pixels.world.maxChunkYLoaded;
	}

	@Override
	public void writeData(CommunicationClient client) throws IOException {
		// TODO Auto-generated method stub
		client.getOutput().writeInt(minChunkXLoaded);
		client.getOutput().writeInt(minChunkYLoaded);
		client.getOutput().writeInt(maxChunkXLoaded);
		client.getOutput().writeInt(maxChunkYLoaded);
	}

	@Override
	public void readData(CommunicationClient client) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	public int minChunkXLoaded, minChunkYLoaded, maxChunkXLoaded, maxChunkYLoaded;

}
