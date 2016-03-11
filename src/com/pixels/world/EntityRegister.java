package com.pixels.world;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.entity.Entity;
import com.pixels.start.Pixels;
import com.pixels.util.Log;
import com.pixels.util.ThreadName;

public class EntityRegister {
	
	public EntityRegister() {
	}
	
	public void update(GameContainer c, int delta, World w) {
		for (Entity e : entityIDMap.values()) {
			e.update(c, delta, w);
			updatePosition(e);
		}
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		for (Entity e : entityIDMap.values()) {
			e.render(c, g, w);
			updatePosition(e);
		}
	}
	
	public void add(Entity entity) {
		
		int id = entity.serverID;
		entityIDMap.put(id, entity);
		addEntityToPositionMap(entity);
		
	}
	
	public void remove(int id) {
		removeEntityFromPositionMap(id);
		entityIDMap.remove(id);
	}
	
	public void remove(Entity e) {
		removeEntityFromPositionMap(e);
		entityIDMap.remove(e.serverID);
	}
	
	public Entity get(int i) {
		return entityIDMap.get(i);
	}
	
	public ArrayList<Entity> get(int x, int y) {
		
		ArrayList<Integer> ids = getIDs(x,y);
		
		if (ids == null)
			return null;
		
		ArrayList<Entity> entities = new ArrayList<Entity>();
		
		for (int id : ids) {
			entities.add(get(id));
		}
		
		return entities;
	}
	
	public ArrayList<Integer> getIDs(int x, int y) {
		
		return entityPositionMap.get(getLocationIndex(x, y));
		
	}
	
	public void renderYGroup(GameContainer c, Graphics g, World w, int y) {
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		int minX = Pixels.world.minChunkXLoaded  << 4;
		int maxX = (Pixels.world.maxChunkXLoaded+1) << 4;
		for (int x = minX; x < maxX; x++) {
			ArrayList<Integer> posGroup = entityPositionMap.get(getLocationIndex(x, y));
			if (posGroup != null)
				ids.addAll(posGroup);
		}
				
		for (int i = 1; i < ids.size(); i++) {
			int next = ids.get(i);
			int j = i;
			while (j > 0 && entityIDMap.get(ids.get(j-1)).posY > entityIDMap.get(next).posY) {
                ids.set(j, ids.get(j-1));
                j--;
            }
			ids.set(j, next);
		}
				
		for (int i = 0; i < ids.size(); i++) {
			entityIDMap.get(ids.get(i)).render(c, g, w);
		}
				
	}
	
	public void merge(EntityRegister register) {
		
		for (Integer key : register.entityIDMap.keySet()) {
			// shoudn't overwrite anything if its working right
			Entity entity = register.get(key);
			int id = entity.serverID;
			Entity cur = entityIDMap.get(id);
			if (cur != null) {
				Log.print(ThreadName.CLIENT, "duplicate entity - need to resolve this");
//				if (cur.posX != entity.posX || cur.posY != entity.posY) {
//					cur.posX = entity.posX;
//					cur.posY = entity.posY;
//					entityIDMap.put(id, cur);
//					updatePosition(cur);
//				}
			} else {
				entityIDMap.put(id, entity);
				addEntityToPositionMap(entity);
			}
		}

		
	}
	
	public void replace(EntityRegister register) {
		
		for (Integer key : entityIDMap.keySet()) {
			if (register.get(key) == null) {
				remove(key);
			} else {
				register.remove(key);
			}
		}
		
		for (Integer key : register.entityIDMap.keySet()) {
			add(register.get(key));
		}
		
	}
	
	public void addEntityToPositionMap(Entity e) {
		int key = getLocationIndex(e);
		e.positionKey = key;
		ArrayList<Integer> entities = entityPositionMap.get(key);
		if (entities == null) {
			entities = new ArrayList<Integer>();
		}
		entities.add(e.serverID);
		entityPositionMap.put(key, entities);
	}
	
	public void removeEntityFromPositionMap(Entity e) {
		int key = e.positionKey;
		ArrayList<Integer> entityMap = entityPositionMap.get(key);
		if (entityMap != null) {
			int i = entityMap.lastIndexOf(e.serverID);
			entityMap.remove(i);
		}
		entityPositionMap.put(key, entityMap);
	}
	
	public void removeEntityFromPositionMap(int id) {
		Entity e = entityIDMap.get(id);
		int key = e.positionKey;
		ArrayList<Integer> entityList = entityPositionMap.get(key);
		if (entityList != null) {
			Object[] ids = entityList.toArray();
			entityList = new ArrayList<Integer>();
			for (int a = 0; a < ids.length; a++) {
				int i = (int) ids[a];
				if (i != e.serverID)
					entityList.add(i);
			}
		}
		entityPositionMap.put(key, entityList);
	}
	
	public void updatePosition(Entity e) {
		if (e.positionKey != getLocationIndex(e)) {
			removeEntityFromPositionMap(e);
			addEntityToPositionMap(e);
		}
	}
	
	public void updatePosition(int i) {
		updatePosition(get(i));
	}
	
	private int getLocationIndex(Entity e) {
		return ((int)e.posY)*(Pixels.world.chunkWidth<<4) + ((int)e.posX);
	}
	
	private int getLocationIndex(int x, int y) {
		return y*(Pixels.world.chunkWidth<<4) + x;
	}
	
	public ConcurrentHashMap<Integer,Entity> entityIDMap = new ConcurrentHashMap<Integer,Entity>();
	public ConcurrentHashMap<Integer,ArrayList<Integer>> entityPositionMap = new ConcurrentHashMap<Integer,ArrayList<Integer>>();

}
