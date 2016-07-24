package com.pixels.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.item.HandCraftingRecipes;
import com.pixels.item.Item;
import com.pixels.start.Pixels;

public class GUIHandCrafting extends GUIComponent {

	public GUIHandCrafting(int x, int y) {
		super(x, y, new GUIComponent[]{new GUIComponent(138, 17, 20, 23, "inventory" + Pixels.t.separator + "triangle.png")});
		input1 = new GUIInventorySlot(0, 0, 0);
		input2 = new GUIInventorySlot(60, 0, 0);
		output = new GUICraftingSlot(180, 0);
		addChild(input1);
		addChild(input2);
		addChild(output);
	}

	public void render(GameContainer c, Graphics g) {
		
		updateCraftingSlots();
		super.render(c, g);
		
	}
	
	private void updateCraftingSlots() {
		if (hasOutput && !output.hasItem()) {
			// the result has been taken out of the output slot
			input1.clearItem();
			input2.clearItem();
			hasOutput = false;
		}
				
		if (input1.hasItem() && input2.hasItem()) {
			//if there's a recipe in the slots
			if (!hasOutput) {
				Item i = HandCraftingRecipes.getOutput(input1.getItem().item, input2.getItem().item);
				if (i != null) {
					output.setItem(new GUIItem(i));
					hasOutput = true;
				}
			}
		} else if (output.hasItem()){
			output.clearItem();
			hasOutput = false;
		}
	}
	
	public GUIInventorySlot input1, input2;
	public GUICraftingSlot output;
	public boolean hasOutput = false;
}
