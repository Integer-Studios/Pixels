package com.pixels.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.input.MouseClickListener;
import com.pixels.item.Item;
import com.pixels.start.Pixels;
import com.pixels.util.TextureLoader;

public class GUIItem extends GUIComponent {
	
	public static GUIItem itemOnMouse;

	public GUIItem(Item i) {
		super(9, 5);
		width = 38;
		height = 38;
		item = i;
		texture = i.texture;
		doesRender = true;
	}
	
	public void render(GameContainer c, Graphics g) {
		
		if (isOnMouse) {
			x = MouseClickListener.getX() - 19;
			y = MouseClickListener.getY() - 19;
		}
		
		super.render(c, g);
		renderDots(c, g);
	}
	
	public void renderDots(GameContainer c, Graphics g) {
		String s = Pixels.t.separator;
		String base = s + "gui" + s + "inventory" + s + "dots" + s;
		if (dots == null) {
			dots = new Image[6];
			dots[0] = TextureLoader.load(base + "green-l.png");
			dots[1] = TextureLoader.load(base + "green-d.png");
			
			dots[2] = TextureLoader.load(base + "yellow-l.png");
			dots[3] = TextureLoader.load(base + "yellow-d.png");
			
			dots[4] = TextureLoader.load(base + "red-l.png");
			dots[5] = TextureLoader.load(base + "red-d.png");
		}
		
		int dotNum = item.getWeight();
		int slotDots = 0;
		
		if (parent != null) {
			slotDots = ((GUIInventorySlot)parent).getWeight();
		}
		
		
		int dotX = -6;
		int slotDotsTodo = slotDots;
		for (int i = 0; i < dotNum; i++) {
			
			int alt = 0;
			int baseIndex = dotNum;
			if (slotDotsTodo != 0) {
				alt = 1;
				baseIndex = slotDots;
				slotDotsTodo--;
			}
			
			if (parent != null)
				dots[(baseIndex-1)*2 + alt].draw(parent.getBaseX() + x + dotX, parent.getBaseY() + y + 39, 9, 9);
			else
				dots[(baseIndex-1)*2 + alt].draw(x + dotX, y + 39, 9, 9);
			
			dotX += 10;
		}
	}
	
	public void putOnMouse() {
		isOnMouse = true;
		GUIItem.itemOnMouse = this;
	}
	
	public void takeOffMouse() {
		isOnMouse = false;
		GUIItem.itemOnMouse = null;
		x = 9;
		y = 5;
	}
	
	public Image[] dots;
	public Item item;
	private boolean isOnMouse = false;

}
