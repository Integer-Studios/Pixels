package com.pixels.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class PacketBlank extends Packet {

	public PacketBlank() {
		
		this.id = 0;
		
	}

	@Override
	public void writeData(DataOutputStream output) {
		// TODO Auto-generated method stub
		System.out.println("writing blank packet");
		
	}

	@Override
	public void readData(DataInputStream input) {
		// TODO Auto-generated method stub
		System.out.println("reading blank packet");
		
	}
	
}
