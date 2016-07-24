package com.pixels.gui;

public class GUICraftingSlot extends GUIInventorySlot {

	public GUICraftingSlot(int x, int y) {
		super(x, y, 0);
	}
	
	@Override
	public void mouseUp() {
		if (GUIItem.itemOnMouse == null && hasItem()){ 
			item.putOnMouse();
			emancipateItem();
		}
	}

}
