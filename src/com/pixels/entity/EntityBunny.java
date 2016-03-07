package com.pixels.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.util.TextureLoader;
import com.pixels.util.Toolkit;
import com.pixels.world.World;

public class EntityBunny extends Entity {
	
	public EntityBunny() {
		this.id = 3;
	}
	
	public void render(GameContainer c, Graphics g, World w) {

		if (texture == null) {
			Toolkit t = new Toolkit();
			String s = t.separator;
			texture = s+"entities"+s+"bunny.png";
		}
		
		if (image == null)  {
			image = TextureLoader.load(texture);
		}
		
		image.draw(posX*w.tileConstant+w.globalOffsetX, posY*w.tileConstant+w.globalOffsetY-(w.tileConstant/2), w.tileConstant, w.tileConstant);
	}

}
