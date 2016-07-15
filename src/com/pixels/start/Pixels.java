package com.pixels.start;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.pixels.communication.CommunicationClient;
import com.pixels.gui.GUI;
import com.pixels.gui.GUIHotbar;
import com.pixels.gui.GUIInventory;
import com.pixels.gui.GUIStatus;
import com.pixels.input.KeyBinder;
import com.pixels.input.KeyBinding;
import com.pixels.input.KeyCode;
import com.pixels.input.KeyboardListener;
import com.pixels.input.MouseClickListener;
import com.pixels.packet.PacketLogin;
import com.pixels.packet.PacketPlayerDidSpawn;
import com.pixels.util.Log;
import com.pixels.util.ThreadName;
import com.pixels.util.Toolkit;
import com.pixels.world.World;

public class Pixels extends BasicGame implements KeyBinder {
	
	public static Pixels game;
	public static World world;
	public static GUI gui;
	public static CommunicationClient client;
	public static Thread communicationThread;
	public static int playerID = 0;
	public static int serverID = -1;
	public static Toolkit t;
		
	public Pixels(String title) throws SlickException {
		super(title);
		
		Log.print(ThreadName.MAIN, "Client Launched with playerID: " + playerID);
		
		// initialize static objects
		t = new Toolkit();
		gui = new GUI();
		
		// initialize client thread
		client = new CommunicationClient("localhost", 25565);
		communicationThread = new Thread(client);
		communicationThread.start();
		
		client.addPacket(new PacketLogin());
		KeyboardListener.addKeyBinding(new KeyBinding("inventory", KeyCode.KEY_I, this));
		
	}
	
	public static void didInitialize() {
		
		world.isLoaded = true;
		client.addPacket(new PacketPlayerDidSpawn());
		
		gui.addComponent(new GUIHotbar());
//		gui.addComponent(new GUIInventory());
		gui.addComponent(new GUIStatus());
		inventory = new GUIInventory();
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
		if (world != null && world.isLoaded) {
			world.render(c, g);
			gui.render(c, g);
		}
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

	@Override
	public void onKeyDown(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyUp(String name) {
		// TODO Auto-generated method stub
		if (name.equals("inventory") && !isInvOpen) {
			gui.addComponent(inventory);
			isInvOpen = true;
		}
		else if (name.equals("inventory") && isInvOpen) {
			gui.removeComponent(inventory.zIndex);
			isInvOpen = false;
		}
		
	}
	
	public static GUIInventory inventory;
	public static boolean isInvOpen = false;

}
