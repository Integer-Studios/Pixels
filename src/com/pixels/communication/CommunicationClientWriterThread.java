package com.pixels.communication;

public class CommunicationClientWriterThread extends Thread {

	CommunicationClient client;

	public CommunicationClientWriterThread (CommunicationClient client) {
		
		this.client = client;

	}
	
	public void run() {
		
		while (client.isRunning()) {
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			if (client.isRunning())
				client.writePacket();
			else 
				break;
			
		}
		
	}

}
