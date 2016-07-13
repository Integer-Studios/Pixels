package com.pixels.gui;

public class GUIStatus extends GUIComponent {

	public GUIStatus() {
		super(20, 20, new GUIComponent[]{
				new GUIHealthIcon(0, 0),
				new GUIHealthIcon(20, 0),
				new GUIHealthIcon(40, 0),
				new GUIHealthIcon(60, 0),
				new GUIHealthIcon(80, 0),
				new GUIHealthIcon(100, 0),
				new GUIHealthIcon(120, 0),
				new GUIHealthIcon(140, 0),
				new GUIHealthIcon(160, 0),
				new GUIHealthIcon(180, 0),
				
				new GUIHungerIcon(2, 20),
				new GUIHungerIcon(22, 20),
				new GUIHungerIcon(42, 20),
				new GUIHungerIcon(62, 20),
				new GUIHungerIcon(82, 20),
				new GUIHungerIcon(102, 20),
				new GUIHungerIcon(122, 20),
				new GUIHungerIcon(142, 20),
				new GUIHungerIcon(162, 20),
				new GUIHungerIcon(182, 20),
				
		});
	}

}
