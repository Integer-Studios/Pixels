package com.pixels.item;

import java.util.HashMap;

public class HandCraftingRecipes {
	
	public static HashMap<String, Integer> recipies = new HashMap<String, Integer>();
	
	public static Item getOutput(Item i1, Item i2) {
		System.out.println(recipies.size());
		String recipie = i1.id + "," + i2.id;
		if (!recipies.containsKey(recipie))
			return null;
		
		return Item.getItemByID(recipies.get(recipie));
	}
	
	static {
		recipies.put("1,0", 1);
	}

}
