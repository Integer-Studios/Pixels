package com.pixels.gui;

import com.pixels.item.Item;

public class GUIInventorySlotGrid extends GUIComponent {

	public GUIInventorySlotGrid(int x, int y, int slotW, int heavy, int medium, int light) {
		
		super(x, y);
		
		slotWidth = slotW;
		heavySlots = heavy;
		mediumSlots = medium;
		lightSlots = light;
		createChildren();
		
	}
	
	private void createChildren() {
		int curX = 0;
		int curY = 0;
		int heavyTodo = heavySlots;
		int mediumTodo = mediumSlots;
		int lightTodo = lightSlots;
		while (heavyTodo > 0) {
			addChild(new GUIInventorySlot(curX * spacing, curY * spacing, 3));
			heavyTodo--;
			curX++;
			if (curX == slotWidth) {
				curX = 0;
				curY++;
			}
		}
		while (mediumTodo > 0) {
			GUIInventorySlot s = new GUIInventorySlot(curX * spacing, curY * spacing, 2);
			s.setItem(new GUIItem(Item.cloudberries));
			addChild(s);
//			addChild(new GUIInventorySlot(curX * spacing, curY * spacing, 2));
			mediumTodo--;
			curX++;
			if (curX == slotWidth) {
				curX = 0;
				curY++;
			}
		}
		while (lightTodo > 0) {
			GUIInventorySlot s = new GUIInventorySlot(curX * spacing, curY * spacing, 1);
			s.setItem(new GUIItem(Item.crowberries));
			addChild(s);
//			addChild(new GUIInventorySlot(curX * spacing, curY * spacing, 1));
			lightTodo--;
			curX++;
			if (curX == slotWidth) {
				curX = 0;
				curY++;
			}
		}
//		GUIInventorySlot s = new GUIInventorySlot(curX * spacing, curY * spacing, 1);
//		s.setItem(new GUIItem(9, 5, Item.test));
//		addChild(s);
	}

	public int slotWidth, heavySlots, mediumSlots, lightSlots;
	private int spacing = 60;

}
