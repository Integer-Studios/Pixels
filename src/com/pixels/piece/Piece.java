package com.pixels.piece;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import com.pixels.packet.PacketHarvestPiece;
import com.pixels.start.Pixels;
import com.pixels.world.World;

public class Piece {
	
	public Piece (int x, int y, int id) {
		
		posX = x;
		posY = y;
		this.id = id;
		collisionBox = new Rectangle(x+((1-info.get(id).collisionWidth)/2), y+(1-info.get(id).collisionHeight), info.get(id).collisionWidth, info.get(id).collisionHeight);
		metadata = 0;
	}
	
	public Piece (int x, int y, int id, int m) {
		this(x,y,id);
		metadata = m;
	}
	
	public void render(GameContainer c, Graphics g, World w) {
		info.get(id).render(c, g, w, this);
	}
	
	public void update(GameContainer c, int delta, World w) {
		info.get(id).update(c, delta, w, this);
	}
	
	public boolean doesCollide() {
		return info.get(id).doesCollide;
	}
	
	public float getCollisionWidth() {
		return info.get(id).collisionWidth;
	}
	
	public float getCollisionHeight() {
		return info.get(id).collisionHeight;
	}
	
	public void harvest(World w) {
		Pixels.client.addPacket(new PacketHarvestPiece(this));
//		w.setPieceID(posX, posY, info.get(id).onHarvestID);
	}
	
	public int getMaxDamage() {
		return info.get(id).maxDamage;
	}

	public void setPieceID(int id) {
		this.id = id;
	}
	
	public void setPieceIDAndMetadata(int id, int metadata) {
		this.id = id;
		this.metadata = metadata;
	}

	public int getPieceID() {
		return id;
	}

	
	public int posX, posY, id, metadata;
	public static ArrayList<PieceInfo> info = new ArrayList<PieceInfo>();
	public Rectangle collisionBox;
	
	static {
		String s = Pixels.t.separator;
		info.add(new PieceBlank());
		info.add(new PieceInfo(s+"pieces"+s+"grass_1.png").setMaxDamage(-1));//1
		info.add(new PieceInfo(s+"pieces"+s+"grass_2.png").setMaxDamage(-1));//2
		info.add(new PieceInfo(s+"pieces"+s+"rock_1.png", 0.2f, 0.1f).setMaxDamage(50));//3
		info.add(new PieceInfo(s+"pieces"+s+"rock_2.png", 0.4f, 0.2f).setMaxDamage(100).setOnHarvestID(3));//4
		info.add(new PieceTall(s+"pieces"+s+"pine.png", 2, 0.1f, 0.2f).setMaxDamage(100));//5
		info.add(new PieceTall(s+"pieces"+s+"apple.png", 2, 0.1f, 0.2f).setMaxDamage(100));//6
		info.add(new PieceTall(s+"pieces"+s+"abyssal-fur.png", 3, 0.1f, 0.2f).setMaxDamage(150));//7
		info.add(new PieceInfo(s+"pieces"+s+"flower_1.png").setMaxDamage(1));//8
		info.add(new PieceBuilding("cabin").setMaxDamage(200));//9
		info.add(new PieceTall(s+"pieces"+s+"cherry.png", 2, 0.1f, 0.2f).setMaxDamage(100));//10
		info.add(new PieceInfo(s+"pieces"+s+"cloudberry.png", 0.2f, 0.1f).setMaxDamage(50).setOnHarvestID(16));//11
		info.add(new PieceInfo(s+"pieces"+s+"crowberry.png", 0.2f, 0.1f).setMaxDamage(50).setOnHarvestID(17));//12
		info.add(new PieceInfo(s+"pieces"+s+"tulip.png").setMaxDamage(1));//13
		info.add(new PieceTall(s+"pieces"+s+"pine_2.png", 2, 0.1f, 0.2f).setMaxDamage(100));//14
		info.add(new PieceInfo(s+"pieces"+s+"rock_3.png", 0.4f, 0.2f).setMaxDamage(100).setOnHarvestID(4));//15
		info.add(new PieceInfo(s+"pieces"+s+"cloudberry-picked.png", 0.2f, 0.1f).setMaxDamage(50));//16
		info.add(new PieceInfo(s+"pieces"+s+"crowberry-picked.png", 0.2f, 0.1f).setMaxDamage(50));//17
		info.add(new PieceInfo(s+"pieces"+s+"shroom.png").setMaxDamage(1));//18
		info.add(new PieceBuilding("gaub").setMaxDamage(200));//19

	}

}
