package com.pixels.item;

import com.pixels.start.Pixels;

public class Item {
	
	public Item(int i, String t) {
		id = i;
		name = t;
		String s = Pixels.t.separator;
		texture = s + "gui" + s + "items" + s + name + ".png";
		weight = 2;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public Item setWeight(int i) {
		weight = i;
		return this;
	}
	
	public static Item test = new Item(0, "item");
	public static Item test2 = new Item(1, "item-2").setWeight(3);
	
	public String texture, name;
	public int id, weight;

}
