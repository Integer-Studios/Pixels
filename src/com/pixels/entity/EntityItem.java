package com.pixels.entity;

import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.pixels.communication.CommunicationClient;
import com.pixels.item.Item;
import com.pixels.start.Pixels;
import com.pixels.util.TextureLoader;
import com.pixels.world.World;

public class EntityItem extends Entity {
	
	public EntityItem() {
		super();
		this.id = 5;
		collisionBox = new Rectangle(0, 0, 0.25f, 0.25f);
	}
	
	public boolean didCollide(Entity e) {
		if (e instanceof EntityPlayer) {
			return false;
		}
		return true;
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		
		if (item == null) {
			System.out.println("wtf entity item render");
			return;
		}
		
		if (image == null)  {
			image = TextureLoader.load(item.texture);
		}
		
		if (shadow == null)  {
			String s = Pixels.t.separator;
			String shadowTexture = s + "entities" + s + "item-shadow.png";
			shadow = TextureLoader.load(shadowTexture);
		}
		
		animationTicker++;
		
		if (animationTicker == animationSpeed) {
			animationTicker = 0;
			if (floatingUp) {
				yOffset--;
				if (yOffset == -2)
					floatingUp = false;
			} else {
				yOffset++;
				if (yOffset == 2)
					floatingUp = true;
			}
		}
		
		shadow.draw(posX*w.tileConstant+w.globalOffsetX-(w.tileConstant/4), posY*w.tileConstant+w.globalOffsetY, w.tileConstant/2, w.tileConstant/4);
		image.draw(posX*w.tileConstant+w.globalOffsetX-(w.tileConstant/4), posY*w.tileConstant+w.globalOffsetY-(w.tileConstant/2) + yOffset, w.tileConstant/2, w.tileConstant/2);
	}
	
	public void writeEntityData(CommunicationClient client) throws IOException {
		client.getOutput().writeInt(item.id);
	}
	
	public void readEntityData(CommunicationClient client) throws IOException {
		item = Item.getItemByID(client.getInput().readInt());
	}
	
	public Item item;
	public Image shadow;
	public int yOffset = 0;
	public boolean floatingUp = true;
	public int animationTicker = 0;
	public int animationSpeed = 6;
}
