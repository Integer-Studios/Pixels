package com.pixels.gui;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class GUIHotbar extends GUIComponent {

	public GUIHotbar() {
		super(20, Display.getHeight()-60, new GUIComponent[]{
				new GUIAbilitySlot(0, 0),
				new GUIAbilitySlot(43, 0),
				new GUIAbilitySlot(86, 0),
				new GUIAbilitySlot(129, 0),
				new GUIAbilitySlot(172, 0),
				new GUIAbilitySlot(215, 0),
				new GUIAbilitySlot(258, 0),
				new GUIAbilitySlot(301, 0),
				new GUIAbilitySlot(344, 0),
				new GUIAbilitySlot(387, 0),
				new GUIEquipSlot(440, 0),
		});
	}
	
	public void render(GameContainer c, Graphics g) {
		this.y = Display.getHeight()-60;
		super.render(c, g);
	}

}
