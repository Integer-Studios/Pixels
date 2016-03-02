package com.pixels.communication;

import com.pixels.packet.Packet;

public class CommunicationClientReaderThread extends Thread  {

	CommunicationClient client;
	Packet packet;
	
	public CommunicationClientReaderThread (CommunicationClient client) {

		this.client = client;
		packet = null;


	}

	public void run(){

		while (client.isRunning()) {
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (packet == null && client.isRunning()) {
				
				packet = client.readPacket();
				
			} else if (packet.loaded && client.isRunning()) {

				packet = null;

			} else {
				
				break;
				
			}
			
		}
		
	}
	
}
