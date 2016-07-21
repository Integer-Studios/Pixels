package com.pixels.gui;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.start.Pixels;
import com.pixels.util.TextureLoader;

public class GUIComponent {
	
	public GUIComponent(int x, int y) {
		
		this.x = x;
		this.y = y;
		children = new HashMap<Integer, GUIComponent>();
		doesRender = false;
		
	}
	
	public GUIComponent(int x, int y, int w, int h, String t) {
		
		this(x, y);
		
		width = w;
		height = h;
		String s = Pixels.t.separator;
		texture = s + "gui" + s + t;
		doesRender = true;
		
	}
	
	public GUIComponent(int x, int y, GUIComponent[] c) {
		
		this(x, y);
		
		for (int i = 0; i < c.length; i++) {
			addChild(c[i]);
		}
		
	}
	
	public GUIComponent(int x, int y, int w, int h, String t, GUIComponent[] c) {
		
		this(x, y, w, h, t);

		for (int i = 0; i < c.length; i++) {
			addChild(c[i]);
		}
		
	}
	
	public void onFirstRender() {
		hasRendered = true;
	}
	
	public void addChild(GUIComponent c) {
		
		c.zIndex = nextZIndex;
		c.parent = this;
		children.put(nextZIndex, c);
		nextZIndex++;
		
	}
	
	public void removeChild(GUIComponent c) {
		children.remove(c.zIndex);
	}
	
	public void emancipate() {
		if (parent != null) {
			parent.removeChild(this);
			Pixels.gui.addComponent(this);
		}
	}
	
	public void adopt(GUIComponent c) {
		Pixels.gui.removeComponent(c);
		addChild(c);
	}
	
	public void setTexture(String t) {
		String s = Pixels.t.separator;
		texture = s + "gui" + s + t;
		image = null;
	}
	

	public void render(GameContainer c, Graphics g) {
		
		if (!hasRendered)
			onFirstRender();
		
		if (doesRender) {
			
			if (image == null)
				image = TextureLoader.load(texture);
			
			if (parent != null)
				image.draw(parent.getBaseX() + x, parent.getBaseY() + y, width, height);
			else
				image.draw(x, y, width, height);
		}
		
		for (GUIComponent component: children.values()) {
			component.render(c, g);
		}
	}
	
	public int getBaseX() {
		if (parent != null) {
			return parent.getBaseX() + x;
		} else {
			return x;
		}
	}
	
	public int getBaseY() {
		if (parent != null) {
			return parent.getBaseY() + y;
		} else {
			return y;
		}
	}
	
	
	public int x, y, width, height;
	public String texture;
	public Image image;
	public boolean doesRender;
	
	public int zIndex = -1;
	public GUIComponent parent;
	public HashMap<Integer, GUIComponent> children;
	public int nextZIndex = 0;
	public boolean hasRendered = false;

}
