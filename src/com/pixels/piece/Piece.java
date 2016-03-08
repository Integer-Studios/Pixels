package com.pixels.piece;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import com.pixels.start.Pixels;
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
		String s = Pixels.t.separator;
		info.add(new PieceInfo());
		info.add(new PieceInfo(s+"pieces"+s+"grass_1.png"));//1
		info.add(new PieceInfo(s+"pieces"+s+"grass_2.png"));//2
		info.add(new PieceInfo(s+"pieces"+s+"rock_1.png"));//3
		info.add(new PieceInfo(s+"pieces"+s+"rock_2.png"));//4
		info.add(new PieceInfoTall(s+"pieces"+s+"pine.png", 2));//5
		info.add(new PieceInfoTall(s+"pieces"+s+"apple.png", 2));//6
		info.add(new PieceInfoTall(s+"pieces"+s+"abyssal-fur.png", 3));//7
		info.add(new PieceInfo(s+"pieces"+s+"flower_1.png"));//8
	}

	public void setPieceID(int id) {
		this.id = id;
	}

	public int getPieceID() {
		return id;
	}

}
