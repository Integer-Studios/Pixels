package com.pixels.start;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.pixels.world.World;

public class Pixels extends BasicGame {
	
	public static Pixels game;
	public static World world;
	
	public Pixels(String title) throws SlickException {
		super(title);
		world = new World(3, 2);
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
		world.render(c, g);
	}

	@Override
	public void init(GameContainer c) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer c, int delta) throws SlickException {
		// TODO Auto-generated method stub
		world.update(c, delta);
	}

}
