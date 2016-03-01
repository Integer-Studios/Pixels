package com.pixels.piece;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.util.Toolkit;
import com.pixels.world.World;

public class Piece {
	
	public Piece (int x, int y, int id) {
		
		posX = x;
		posY = y;
		this.id = id;
		
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		info.get(id).render(c, g, w, this);
	}
	
	public void update(GameContainer c, int delta, World w) {
		info.get(id).update(c, delta, w, this);
	}
	
	public int posX, posY, id;
	public static ArrayList<PieceInfo> info = new ArrayList<PieceInfo>();
	
	static {
		Toolkit t = new Toolkit();
		String s = t.separator;
		info.add(new PieceInfo());
		info.add(new PieceInfo(s+"pieces"+s+"grass.png"));
		info.add(new PieceInfo(s+"pieces"+s+"rock.png"));
	}

	public void setPieceID(int id) {
		this.id = id;
	}

	public int getPieceID() {
		return id;
	}

}
