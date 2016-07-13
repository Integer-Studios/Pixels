package com.pixels.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUIInventory extends GUIComponent {
	
	public GUIInventory() {
		super(20, Display.getHeight()-242, new GUIComponent[]{
				new GUIItemSlot(0, 0),
				new GUIItemSlot(43, 0),
				new GUIItemSlot(86, 0),
				new GUIItemSlot(129, 0),
				new GUIItemSlot(172, 0),
				new GUIItemSlot(215, 0),
				new GUIItemSlot(258, 0),
				new GUIItemSlot(301, 0),
				new GUIItemSlot(344, 0),
				new GUIItemSlot(387, 0),
				
				new GUIItemSlot(0, 43),
				new GUIItemSlot(43, 43),
				new GUIItemSlot(86, 43),
				new GUIItemSlot(129, 43),
				new GUIItemSlot(172, 43),
				new GUIItemSlot(215, 43),
				new GUIItemSlot(258, 43),
				new GUIItemSlot(301, 43),
				new GUIItemSlot(344, 43),
				new GUIItemSlot(387, 43),
				
				new GUIItemSlot(0, 86),
				new GUIItemSlot(43, 86),
				new GUIItemSlot(86, 86),
				new GUIItemSlot(129, 86),
				new GUIItemSlot(172, 86),
				new GUIItemSlot(215, 86),
				new GUIItemSlot(258, 86),
				new GUIItemSlot(301, 86),
				new GUIItemSlot(344, 86),
				new GUIItemSlot(387, 86),
				
				new GUIItemSlot(0, 129),
				new GUIItemSlot(43, 129),
				new GUIItemSlot(86, 129),
				new GUIItemSlot(129, 129),
				new GUIItemSlot(172, 129),
				new GUIItemSlot(215, 129),
				new GUIItemSlot(258, 129),
				new GUIItemSlot(301, 129),
				new GUIItemSlot(344, 129),
				new GUIItemSlot(387, 129),
				
				new GUIEquipSlot(440, 0),
				new GUIEquipSlot(440, 43),
				new GUIEquipSlot(440, 86),
				new GUIEquipSlot(440, 129),
		});
	}
	
	public void render(GameContainer c, Graphics g) {
		this.y = Display.getHeight()-242;
		super.render(c, g);
	}
	
}
