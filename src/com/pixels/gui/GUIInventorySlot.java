package com.pixels.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.start.Pixels;

public class GUIInventorySlot extends GUIClickable {

	public GUIInventorySlot(int x, int y, int w) {
		super(x, y, 55, 55, "inventory" + Pixels.t.separator + "green.png");
		weight = w;
		switch (weight) {
		case 1:
			setTexture("inventory" + Pixels.t.separator + "green.png");
			break;
		case 2:
			setTexture("inventory" + Pixels.t.separator + "yellow.png");
			break;
		case 3:
			setTexture("inventory" + Pixels.t.separator + "red.png");
			break;
		}
	}
	
	public void setItem(GUIItem i) {
		item = i;
		adopt(item);
	}
	
	public GUIItem getItem() {
		return item;
	}
	
	public void clearItem() {
		removeChild(item);
		item = null;
	}
	
	public void emancipateItem() {
		item.emancipate();
		item = null;
	}
	
	public boolean hasItem() {
		return item != null;
	}
	
	public int getWeight() {
		return weight;
	}
	
	@Override
	public void mouseDown() {
		// TODO Auto-generated method stub
		
	}
	
	public void render(GameContainer c, Graphics g) {
		super.render(c, g);
	}

	@Override
	public void mouseUp() {
		// TODO Auto-generated method stub
		if (hasItem()) {
			if (GUIItem.itemOnMouse != null){
				GUIItem i = GUIItem.itemOnMouse;
				i.takeOffMouse();
				item.putOnMouse();
				emancipateItem();
				setItem(i);
			} else {
				item.putOnMouse();
				emancipateItem();
			}
		} else if (GUIItem.itemOnMouse != null){
			setItem(GUIItem.itemOnMouse);
			item.takeOffMouse();
		}
	}
	
	public int weight;
	private GUIItem item;
	
}
