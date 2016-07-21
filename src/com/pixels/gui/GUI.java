package com.pixels.gui;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUI {
	
	public GUI() {
		components = new HashMap<Integer, GUIComponent>();
	}
	
	public void addComponent(GUIComponent c) {
		c.zIndex = nextZIndex;
		c.parent = null;
		components.put(nextZIndex, c);
		nextZIndex++;
	}
	
	public void removeComponent(int z) {
		components.remove(z);
	}
	
	public void removeComponent(GUIComponent c) {
		removeComponent(c.zIndex);
	}
	
	public void render(GameContainer c, Graphics g) {
		for (GUIComponent component: components.values()) {
			component.render(c, g);
		}
	}
	
	public HashMap<Integer, GUIComponent> components;
	public int nextZIndex = 0;

}
