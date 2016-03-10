package com.pixels.communication;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.pixels.packet.Packet;
import com.pixels.util.Log;
import com.pixels.util.ThreadName;

public class CommunicationClient implements Runnable {
	
	public CommunicationClient(String host, int port) {
		
		this.host = host;
		this.port = port;
		
	}

	@Override
	public void run() {
		
		try {
			
			socket = new Socket(host, port);
			socket.setTcpNoDelay(true);
			output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), 5120));
			input = new DataInputStream(socket.getInputStream());
			writer = new CommunicationClientWriterThread(this);
			reader = new CommunicationClientReaderThread(this);
			running = true;
			
			writer.start();
			reader.start();
			
			Log.print(ThreadName.CLIENT, "Client initialized");

		} catch (UnknownHostException e) {
			Log.error(ThreadName.CLIENT, "Don't know about host: " + host + ".");
			System.exit(1);
		} catch (IOException e) {
			Log.error(ThreadName.CLIENT, "Couldn't get I/O for the connection");
			e.printStackTrace();
		}
		
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public DataInputStream getInput() {
		return input;
	}
	
	public DataOutputStream getOutput() {
		return output;
	}
	
	public void addPacket(Packet packet) {

		packetQue.add(packet);
		
	}
	
	public Packet readPacket() {
		try {
			
			return Packet.readPacket(this);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	public boolean writePacket() {
				
		try {

			if (packetQue.size() > 0) {

				Packet packet = packetQue.get(0);

				if (packet != null) {

					packetQue.remove(0);
					Packet.writePacket(packet, this);
					return true;

				}
				
			}
			
		} catch (Exception e) {
			Log.error(ThreadName.CLIENT, "Failed to write packet");
			e.printStackTrace();
		}
		return false;
	}
	
	public void disconnect() {
		
		try {
			Log.print(ThreadName.CLIENT, "Disconnecting client");
			output.flush();
		    output.close();
			input.close();
			socket.close();
			running = false;
			packetQue.clear();

		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public String host;
    public int port;
    public Socket socket;
	private volatile Thread reader;
	private volatile Thread writer;
	private volatile DataInputStream input;
	private volatile DataOutputStream output;
	private boolean running = false;
	private ArrayList<Packet> packetQue = new ArrayList<Packet>();

}
