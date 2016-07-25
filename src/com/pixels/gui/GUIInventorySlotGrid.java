package com.pixels.gui;

import java.util.HashMap;

import com.pixels.item.Item;

public class GUIInventorySlotGrid extends GUIComponent {

	public GUIInventorySlotGrid(int x, int y, int slotW, GUIInventory i) {
		
		super(x, y);
		
		slotWidth = slotW;
		guiInventory = i;
		createChildren();
		
	}
	
	private void createChildren() {
		int index = 0;
		for (int i = 0; i < guiInventory.getInventory().heavySlots; i++) {
			setSlot(index, 3);
			index++;
		}
		for (int i = 0; i < guiInventory.getInventory().mediumSlots; i++) {
			setSlot(index, 2);
			index++;
		}
		for (int i = 0; i < guiInventory.getInventory().lightSlots; i++) {
			setSlot(index, 1);
			index++;
		}
	}
	
	public void setSlot(int i, int w) {
		if (slots.containsKey(i)) {
			removeChild(slots.get(i));
		}
		slots.put(i, new GUIInventorySlot((i%slotWidth) * spacing, ((int)(i/slotWidth)) * spacing, w, i, guiInventory));
		addChild(slots.get(i));
	}
	
	public void setItem(int i, Item item) {
		slots.get(i).setItem(new GUIItem(item));
	}


	public int slotWidth;
	private int spacing = 60;
	public GUIInventory guiInventory;
	public HashMap<Integer, GUIInventorySlot> slots = new HashMap<Integer, GUIInventorySlot>();

}
