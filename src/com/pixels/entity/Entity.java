package com.pixels.entity;

import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

import com.pixels.communication.CommunicationClient;
import com.pixels.piece.Piece;
import com.pixels.util.TextureLoader;
import com.pixels.util.Toolkit;
import com.pixels.world.World;

public class Entity {
	
	public Entity() { 
		collisionBox = new Rectangle(0, 0, 0, 0);
	}
	
	public void construct(int id, int key, float x, float y) {
		setPosition(x,  y);
		serverID = id;
		positionKey = key;
		collisionBox = new Rectangle(0, 0, 0, 0);
	}
	
	public void setPosition(float x, float y) {
		posX = x;
		posY = y;
	}

	public void update(GameContainer c, int delta, World w) {
		
		collisionBox.setLocation(posX - (collisionBox.getWidth()/2), posY - collisionBox.getHeight());
		
		if (this instanceof EntityPlayer) {
			w.checkEntityCollisions(this);
		}
		
		this.setPosition(posX + velocityX, posY + velocityY);
		
		prevVelocityX = velocityX;
		prevVelocityY = velocityY;
		prevPosX = posX;
		prevPosY = posY;

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
		
		image.draw(posX*w.tileConstant+w.globalOffsetX-(w.tileConstant/2), posY*w.tileConstant+w.globalOffsetY-(w.tileConstant/2), w.tileConstant, w.tileConstant);
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
	
	public float getVelocityY() {
		return velocityY;
	}
	
	public float getVelocityX() {
		return velocityX;
	}
	
	public float getPreviousVelocityY() {
		return prevVelocityY;
	}
	
	public float getPreviousVelocityX() {
		return prevVelocityX;
	}
	
	public void setVelocity (float x, float y) {
		velocityX = x;
		velocityY = y;
	}
	
	public void writeEntityData(CommunicationClient client) throws IOException {
		
	}
	
	public void readEntityData(CommunicationClient client) throws IOException {
		
	}
	
	public void setCollisionBoxSize(float width, float height) {
		collisionBox.setSize(width, height);
	}
	
	public float getCollisionWidth() {
		return collisionBox.getWidth();
	}
	
	public float getCollisionHeight() {
		return collisionBox.getHeight();
	}
	
	public boolean didCollide(Entity e) {
		return true;
	}
	
	public boolean didCollide(Piece p) {
		return true;
	}
	
	public int id, serverID, positionKey;
	public float posX, posY, prevPosX, prevPosY;
	public float velocityX, velocityY;
	public float prevVelocityX, prevVelocityY;
	public Image image;
	public String texture;
	public Rectangle collisionBox;

	
	@SuppressWarnings("rawtypes")
	private static HashMap<Integer, Class> entityMap = new HashMap<Integer, Class>();
	
	static {
		
		entityMap.put(0, EntityBlank.class);
		entityMap.put(1, EntityPlayer.class);
		entityMap.put(2, EntityOnlinePlayer.class);
		entityMap.put(3, EntityGob.class);
		entityMap.put(4, EntityBear.class);
		entityMap.put(5, EntityItem.class);

	}

}
