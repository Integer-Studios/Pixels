package com.pixels.communication;

public class CommunicationClientWriterThread extends Thread {

	CommunicationClient client;

	public CommunicationClientWriterThread (CommunicationClient client) {
		
		this.client = client;

	}
	
	public void run() {
		
		while (client.isRunning()) {
			
			if (client.isRunning())
				client.writePacket();
			else 
				break;
			
		}
		
	}

}
