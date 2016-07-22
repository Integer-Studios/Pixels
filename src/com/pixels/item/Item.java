package com.pixels.item;

import com.pixels.start.Pixels;

public class Item {
	
	public Item(int i, String t) {
		id = i;
		name = t;
		String s = Pixels.t.separator;
		texture = s + "gui" + s + "items" + s + name + ".png";
		weight = 1;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public Item setWeight(int i) {
		weight = i;
		return this;
	}
	
	public static Item cloudberries = new Item(0, "cloudberry");
	public static Item crowberries = new Item(1, "crowberry");
	
	public String texture, name;
	public int id, weight;

}
