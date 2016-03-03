package com.pixels.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pixels.communication.CommunicationClient;
import com.pixels.start.Pixels;

public abstract class Packet {
	
	public static void writePacket(Packet packet, CommunicationClient client) {

		packet.userID = Pixels.playerID;
		DataOutputStream output = client.getOutput();
		try {
			output.writeInt(packet.id);
			output.writeInt(packet.userID);
			packet.writeAuxiliaryVariables(output);
			packet.writeData(client);
			output.flush();

			if (packet.id == 9) {
				
				client.disconnect();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Packet readPacket(CommunicationClient client) {
		
		try {
			
			DataInputStream input = client.getInput();
			int id = input.readInt();
				
			int userID = input.readInt();

			Packet packet = getPacket(id);

			if (packet == null) 
				System.out.println("[Pixels] Skipping packet with id: " + id);
			
			packet.userID = userID;
			packet.id = id;
			packet.readAuxiliaryVariables(input);
			packet.readData(client);
			packet.loaded = true;

			return packet;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			if (client.isRunning()) {
				client.disconnect();
				System.err.println("Client lost the connection");
				
			}

		} 
		
		return null;
		
	}
	
	public static void writeString(String string, DataOutputStream output) throws IOException {
        if (string.length() > 32767)
        {
            throw new IOException("String too big");
        }
        else
        {
            output.writeShort(string.length());
            output.writeChars(string);
        }
    }

    public static String readString(int length, DataInputStream input) throws IOException {
       
    	short lengthRead = input.readShort();

        if (lengthRead > length)
        {
            throw new IOException("Received string length longer than maximum allowed (" + lengthRead + " > " + length + ")");
        }
        else if (lengthRead < 0)
        {
            throw new IOException("Received string length is less than zero! Weird string!");
        }
        else
        {
            StringBuilder builder = new StringBuilder();

            for (int x = 0; x < lengthRead; ++x)
            {
            	builder.append(input.readChar());
            }

            return builder.toString();
        }
    }
    
    @SuppressWarnings("rawtypes")
	public static Packet getPacket(int id) {
        try
        {
            Class packetClass = (Class)packetMap.get(id);
            return packetClass == null ? null : (Packet)packetClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Skipping packet with id " + id);
            return null;
        }
    }
    
    public void writeAuxiliaryVariables(DataOutputStream output) throws IOException {
		
		output.writeInt(auxiliaryFloats.size());
		output.writeInt(auxiliaryIntegers.size());
		output.writeInt(auxiliaryBooleans.size());
		output.writeInt(auxiliaryStrings.size());

		for (Float f : auxiliaryFloats) {

			output.writeFloat(f);

		}

		for (Integer i : auxiliaryIntegers) {

			output.writeInt(i);

		}
		
		for (Boolean b : auxiliaryBooleans) {

			output.writeBoolean(b);

		}

		for (String s : auxiliaryStrings) {

			output.writeInt(s.length());
			writeString(s, output);

		}
		
	}
	
	public void readAuxiliaryVariables(DataInputStream input) throws IOException {
		
		int floats = input.readInt();
		int ints = input.readInt();
		int booleans = input.readInt();
		int strings = input.readInt();
		
		for (int x = 0; x < floats; x ++) {

			auxiliaryFloats.add(input.readFloat());

		}

		for (int x = 0; x < ints; x ++) {

			auxiliaryIntegers.add(input.readInt());

		}

		for (int x = 0; x < booleans; x ++) {

			auxiliaryBooleans.add(input.readBoolean());

		}

		for (int x = 0; x < strings; x ++) {
			
			int length = input.readInt();
			auxiliaryStrings.add(readString(length, input));

		}


	}

	public Packet addAuxiliaryFloat(float f) {

		auxiliaryFloats.add(f);
		return this;

	}

	public Packet addAuxiliaryInteger(Integer integer) {

		auxiliaryIntegers.add(integer);
		return this;

	}

	public Packet addAuxiliaryBoolean(Boolean bool) {

		auxiliaryBooleans.add(bool);
		return this;

	}
	
	public Packet addAuxiliaryString(String string) {

		auxiliaryStrings.add(string);
		return this;

	}

	public Packet addAuxiliaryFloats(float[] floats) {

		for (int x = 0; x < floats.length; x ++) {
			
			auxiliaryFloats.add(floats[x]);
		
		}
			
		return this;

	}
	
	public Packet addAuxiliaryIntegers(int[] integers) {

		for (int x = 0; x < integers.length; x ++) {
			
			auxiliaryIntegers.add(integers[x]);
		
		}
		return this;

	}
	
	public Packet addAuxiliaryBooleans(Boolean[] booleans) {

		for (int x = 0; x < booleans.length; x ++) {
			
			auxiliaryBooleans.add(booleans[x]);
		
		}
		return this;

	}
	
	public Packet addAuxiliaryStrings(String[] strings) {

		for (int x = 0; x < strings.length; x ++) {
			
			auxiliaryStrings.add(strings[x]);
		
		}
		return this;

	}
	
	public abstract void writeData(CommunicationClient client) throws IOException;
	
	public abstract void readData(CommunicationClient client) throws IOException;

	public int id;
	public boolean loaded;
	public int userID;
	public ArrayList<Float> auxiliaryFloats = new ArrayList<Float>();
	public ArrayList<Integer> auxiliaryIntegers = new ArrayList<Integer>();
	public ArrayList<Boolean> auxiliaryBooleans = new ArrayList<Boolean>();
	public ArrayList<String> auxiliaryStrings = new ArrayList<String>();
	
	@SuppressWarnings("rawtypes")
	private static HashMap<Integer, Class> packetMap = new HashMap<Integer, Class>();
	
	static {
		
		packetMap.put(0, PacketBlank.class);
		packetMap.put(1, PacketLogin.class);
		packetMap.put(2, PacketSpawn.class);
		packetMap.put(3, PacketWorldData.class);
		packetMap.put(4, PacketPlayerDidSpawn.class);
		packetMap.put(5, PacketUpdateEntity.class);
		packetMap.put(6, PacketSpawnEntity.class);
		
	}
	
}
