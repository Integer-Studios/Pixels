package com.pixels.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.input.InterfaceManager;
import com.pixels.input.KeyBinder;
import com.pixels.input.KeyBinding;
import com.pixels.input.KeyCode;
import com.pixels.item.Inventory;
import com.pixels.item.Item;
import com.pixels.start.Pixels;

public class GUIInventory extends GUIComponent implements KeyBinder {
	
	public Inventory inventory;
	public GUIInventorySlotGrid slotGrid;
	public GUIHandCrafting crafting;

	public GUIInventory(Inventory i) {
		super((int)(Display.getWidth()/2), (int)(Display.getHeight()/2), 255, 338, "inventory" + Pixels.t.separator + "window.png");
		
		inventory = i;
		crafting = new GUIHandCrafting(10, 20);
		slotGrid = new GUIInventorySlotGrid(10, 93, 4, this);
		addChild(crafting);
		addChild(slotGrid);
		InterfaceManager.guiInterface.addKeyBinding(new KeyBinding("close-inventory", KeyCode.KEY_I, this));
		
	}
	
	public void render(GameContainer c, Graphics g) {
		x = (int)(Display.getWidth()/2)-(width/2);
		y = (int)(Display.getHeight()/2)-(height/2);
		super.render(c, g);
	}

	@Override
	public void onKeyDown(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyUp(String name) {
		if (name.equals("close-inventory") && GUIItem.itemOnMouse == null) {
			InterfaceManager.setCurrentInterface("world");
			Pixels.gui.removeComponent(this);
		}
	}
	
	public void setWeight(int i, int w) {
		slotGrid.setSlot(i, w);
	}
	
	public void setItem(int i, Item item) {
		slotGrid.setItem(i, item);
	}
	
	public Inventory getInventory() {
		return inventory;
	}

}
