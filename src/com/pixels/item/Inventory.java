package com.pixels.item;

import java.util.HashMap;

import com.pixels.gui.GUIInventory;
import com.pixels.input.InterfaceManager;
import com.pixels.input.KeyBinder;
import com.pixels.input.KeyBinding;
import com.pixels.input.KeyCode;
import com.pixels.start.Pixels;

public class Inventory implements KeyBinder {
	
	public Inventory(int h, int m, int l) {
		
		heavySlots = h;
		mediumSlots = m;
		lightSlots = l;
		
		for (int i = 0; i < h; i++) {
			weights.put(i, 3);
			items.put(i, null);
		}
		for (int i = 0; i < m; i++) {
			weights.put(i, 2);
		}
		for (int i = 0; i < l; i++) {
			weights.put(i, 1);
		}
		
		guiInventory = new GUIInventory(this);
		InterfaceManager.worldInterface.addKeyBinding(new KeyBinding("inventory", KeyCode.KEY_I, this));
		InterfaceManager.guiInterface.addKeyBinding(new KeyBinding("inventory", KeyCode.KEY_I, this));

	}
	
	public int getWeight(int i) {
		return weights.get(i);
	}
	
	public void setWeight(int i, int w) {
		weights.put(i, w);
		guiInventory.setWeight(i, w);
	}
	
	public Item getItem(int i) {
		return items.get(i);
	}
	
	public void addItem(Item item) {
		for (int i = 0; i < (heavySlots+mediumSlots+lightSlots); i++) {
			if (items.get(i) == null) {
				setItem(i, item);
				return;
			}
		}
	}
	
	public void setItem(int i, Item item) {
		items.put(i, item);
		guiInventory.setItem(i, item);
	}
	
	public void guiSetItem(int i, Item item) {
		items.put(i, item);
	}
	
	public void open() {
		InterfaceManager.setCurrentInterface("gui");
		Pixels.gui.addComponent(guiInventory);
		isOpen = true;
	}
	
	public void close() {
		InterfaceManager.setCurrentInterface("world");
		Pixels.gui.removeComponent(guiInventory);
		isOpen = false;
	}

	@Override
	public void onKeyDown(String name) {
	}
	
	@Override
	public void onKeyUp(String name) {
		
		if (name.equals("inventory")) {
			if (!isOpen)
				open();
			else
				close();
		}
		
	}
	
	public HashMap<Integer, Item> items = new HashMap<Integer, Item>();
	public HashMap<Integer, Integer> weights = new HashMap<Integer, Integer>();
	public int heavySlots, mediumSlots, lightSlots;
	public boolean isOpen = false;
	public GUIInventory guiInventory;

}
