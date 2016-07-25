package com.pixels.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.start.Pixels;

public class GUIInventorySlot extends GUIClickable {

	public GUIInventorySlot(int x, int y, int w, int i, GUIInventory inv) {
		super(x, y, 55, 55, "inventory" + Pixels.t.separator + "green.png");
		weight = w;
		index = i;
		guiInventory = inv;
		switch (weight) {
		case 0:
			setTexture("inventory" + Pixels.t.separator + "brown.png");
			break;
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
	
	public GUIInventorySlot(int x, int y, int w) {
		this(x, y, w, 0, null);
	}
	
	public void setItem(GUIItem i) {
		item = i;
		adopt(item);
	}
	
	public GUIItem getItem() {
		return item;
	}
	
	public void clearItem() {
		if (hasItem()) {
			removeChild(item);
			item = null;
		}
	}
	
	public void emancipateItem() {
		if (hasItem()) {
			item.emancipate();
			item = null;
		}
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
	
	private void notifyInventory() {
		if (hasInventory()) {
			if (hasItem())
				guiInventory.getInventory().guiSetItem(index, item.item);
			else
				guiInventory.getInventory().guiSetItem(index, null);
		}
	}
	
	public boolean hasInventory() {
		return guiInventory != null;
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
				notifyInventory();
			} else {
				item.putOnMouse();
				emancipateItem();
				notifyInventory();
			}
		} else if (GUIItem.itemOnMouse != null){
			setItem(GUIItem.itemOnMouse);
			item.takeOffMouse();
			notifyInventory();
		}
	}
	
	public int weight;
	public GUIItem item;
	public GUIInventory guiInventory;
	public int index;
	
}
