package com.pixels.start;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.pixel.input.KeyboardListener;
import com.pixel.input.MouseClickListener;
import com.pixels.communication.CommunicationClient;
import com.pixels.packet.PacketLogin;
import com.pixels.world.World;

public class Pixels extends BasicGame {
	
	public static Pixels game;
	public static World world;
	public static CommunicationClient client;
	public static Thread communicationThread;
	public static int playerID = 1;
	public static int serverID = -1;
		
	public Pixels(String title) throws SlickException {
		super(title);
		client = new CommunicationClient("192.168.0.5", 25565);
		communicationThread = new Thread(client);
		communicationThread.start();
		client.addPacket(new PacketLogin());
	}

	public static void main(String[] args) {
		
		try {
			initializeLWJGL();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	

	}
	
	public static void initializeLWJGL() throws SlickException {
		
		Pixels game = new Pixels("Pixels");
		
		AppGameContainer container = new AppGameContainer(game);
        container.setDisplayMode(900, 600, false);
        container.setVSync(true);
        container.setMaximumLogicUpdateInterval(35); 
        container.setMinimumLogicUpdateInterval(25);

        Pixels.game = game;

		container.start();
		
	}
	
	@Override
	public void render(GameContainer c, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		if (world != null && world.isLoaded)
			world.render(c, g);
	}

	@Override
	public void init(GameContainer c) throws SlickException {
		// TODO Auto-generated method stub
		c.getInput().addKeyListener(new KeyboardListener());
		c.getInput().addMouseListener(new MouseClickListener());
	}

	@Override
	public void update(GameContainer c, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (world != null)
			world.update(c, delta);
	}

}
