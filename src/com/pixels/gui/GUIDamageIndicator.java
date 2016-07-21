package com.pixels.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.pixels.entity.EntityPlayer;
import com.pixels.piece.Piece;
import com.pixels.start.Pixels;
import com.pixels.util.TextureLoader;

public class GUIDamageIndicator extends GUIComponent {

	public GUIDamageIndicator(EntityPlayer pl, Piece p) {
		super(0, 0, 64, 8, "damage-indicator" + Pixels.t.separator + "0.png");
		player = pl;
		piece = p;
	}
	
	@Override
	public void render(GameContainer c, Graphics g) {
		
		if (stages == null) {
			stages = new Image[12];
			String s = Pixels.t.separator;
			for (int i = 0; i < 12; i++) {
				stages[i] = TextureLoader.load(s + "gui" + s + "damage-indicator" + s + i + ".png");
			}
			image = stages[0];
		}
		
		x = (piece.posX * Pixels.world.tileConstant) + Pixels.world.globalOffsetX;
		y = ((piece.posY + 1) * Pixels.world.tileConstant) + Pixels.world.globalOffsetY + 5;

		float percent = (float)player.currentPieceDamage / (float)piece.getMaxDamage();
		int stage = (int)((percent*100) / 8.333f);
		
		if (currentStage != stage) {
			currentStage = stage;
			image = stages[currentStage];
		}
		
		super.render(c, g);
	}
	
	public Piece piece;
	public EntityPlayer player;
	public int currentStage = 0;
	public Image[] stages;

}
