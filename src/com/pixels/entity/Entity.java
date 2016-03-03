package com.pixels.entity;

import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.communication.CommunicationClient;
import com.pixels.start.Pixels;
import com.pixels.util.TextureLoader;
import com.pixels.util.Toolkit;
import com.pixels.world.World;

public class Entity {
	
	public Entity() { }
	
	public Entity(int x, int y, boolean prop) {
		posX = x;
		posY = y;
		if (prop)
			serverID = Pixels.world.propogateEntity(this);
	}
	
	public void initializePosition(int x, int y) {
		//used only for pre propogation
		posX = x;
		posY = y;
	}
	
	public void setPosition(int x, int y, World w) {
		w.moveEntity(serverID, x, y);
	}

	public void update(GameContainer c, int delta, World w) {

	}
	
	public void render(GameContainer c, Graphics g, World w) {
		
		if (texture == null) {
			Toolkit t = new Toolkit();
			String s = t.separator;
			texture = s+"entities"+s+"temp.png";
		}
		
		if (image == null)  {
			image = TextureLoader.load(texture);
		}
		
		image.draw(posX*w.tileConstant+w.globalOffsetX, posY*w.tileConstant+w.globalOffsetY, w.tileConstant, w.tileConstant);
	}

	public int getServerID() {
		return serverID;
	}
	
	@SuppressWarnings("rawtypes")
	public static Entity getEntity(int id) {
        try
        {
            Class entityClass = (Class)entityMap.get(id);
            return entityClass == null ? null : (Entity)entityClass.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    } 
	
	public void writeEntityData(CommunicationClient client) throws IOException {
		
	}
	
	public void readEntityData(CommunicationClient client) throws IOException {
		
	}
	
	public int id, posX, posY, serverID;
	public Image image;
	public String texture;
	
	@SuppressWarnings("rawtypes")
	private static HashMap<Integer, Class> entityMap = new HashMap<Integer, Class>();
	
	static {
		
		entityMap.put(0, EntityBlank.class);
		entityMap.put(1, EntityPlayer.class);
		entityMap.put(2, EntityOnlinePlayer.class);

	}

}
