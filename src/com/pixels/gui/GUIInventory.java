package com.pixels.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.input.InterfaceManager;
import com.pixels.input.KeyBinder;
import com.pixels.input.KeyBinding;
import com.pixels.input.KeyCode;
import com.pixels.start.Pixels;

public class GUIInventory extends GUIComponent implements KeyBinder {

	public GUIInventory() {
		super((int)(Display.getWidth()/2), (int)(Display.getHeight()/2), 255, 338, "inventory" + Pixels.t.separator + "window.png", new GUIComponent[]{
				new GUIInventorySlotGrid(10, 93, 4, 4, 4, 8),
				new GUIInventorySlot(10, 20, 0)
		});
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

}
