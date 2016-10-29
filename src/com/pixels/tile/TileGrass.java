package com.pixels.tile;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.newdawn.slick.Image;
import org.newdawn.slick.util.BufferedImageUtil;

import com.pixels.start.Pixels;
import com.pixels.world.World;

public class TileGrass extends TileInfo {

	public TileGrass() {
		super(Pixels.t.separator+"tiles"+Pixels.t.separator+"grass");
		baseMap = new HashMap<Integer, Image>();
		downOverlaysMap = new HashMap<Integer, HashMap<Integer, Image>>();
	}
	
	protected void renderBase(World w, Tile t) {
		Image image = getColorizedBase(t);
		image.draw(t.posX*w.tileConstant+w.globalOffsetX, t.posY*w.tileConstant+w.globalOffsetY, w.tileConstant, w.tileConstant);
	}

	protected void renderDownOverlay(World w, Tile t, int i) {
		
		Image image = getColorizedDownOverlay(t, i);
		image.draw(t.posX*w.tileConstant+w.globalOffsetX, t.posY*w.tileConstant+w.globalOffsetY, w.tileConstant, w.tileConstant);
	}
	
	private Image getColorizedDownOverlay(Tile t, int i) {

		if (t.tempurature < 32 && t.humidity < 90) {
			
			if (!downOverlaysMap.containsKey(snowKey) || downOverlaysMap.get(snowKey) == null) {
				downOverlaysMap.put(snowKey, new HashMap<Integer, Image>());
			}
			if (!downOverlaysMap.get(snowKey).containsKey(i) || downOverlaysMap.get(snowKey).get(i) == null) {
				downOverlaysMap.get(snowKey).put(i, filterTileImage("down" + Pixels.t.separator + downTextures[i], 0, true));
			}
			
			return downOverlaysMap.get(snowKey).get(i);
			
		} else {
			
			int redValue = getRedValue(t);
			if (!downOverlaysMap.containsKey(redValue) || downOverlaysMap.get(redValue) == null) {
				downOverlaysMap.put(redValue, new HashMap<Integer, Image>());
			}
			if (!downOverlaysMap.get(redValue).containsKey(i) || downOverlaysMap.get(redValue).get(i) == null) {
				downOverlaysMap.get(redValue).put(i, filterTileImage("down" + Pixels.t.separator + downTextures[i], redValue, false));
			}
			
			return downOverlaysMap.get(redValue).get(i);
			
		}
		
	}
	
	private Image getColorizedBase(Tile t) {

		if (t.tempurature < 32 && t.humidity < 90) {
			if (!baseMap.containsKey(snowKey) || baseMap.get(snowKey) == null) {
				baseMap.put(snowKey, filterTileImage("base.png", 0, true));
			}
			return baseMap.get(snowKey);
		} else {
			int redValue = getRedValue(t);
			if (!baseMap.containsKey(redValue) || baseMap.get(redValue) == null) {
				baseMap.put(redValue, filterTileImage("base.png", redValue, false));
			}
			return baseMap.get(redValue);
		}
		
	}
	
	private int getRedValue(Tile t) {
		int i = t.humidity;
		// 0-170
		float f = i/5f;
		// 0-34
		int redValue = ((int)f)*5;
		//0-170 discrete in 34 groups
		return redValue;
	}
	
	private Image filterTileImage(String texture, int redValue, boolean snow) {

		try {
			String s = Pixels.t.separator;
			File file = new File("resources" + s + "tiles" + s + "grass" + s + texture);
			BufferedImage bufImg = ImageIO.read(file);
			for (int y = 0; y < bufImg.getHeight(); y++) {
				for (int x = 0; x < bufImg.getWidth(); x++) {
					int color = bufImg.getRGB(x, y);
					if (color == -9332910) {
						//middle
						if (snow) {
							bufImg.setRGB(x, y, new Color(244,255,255, 255).getRGB());
						} else {
							int[] rgb = getRGB(color);
							bufImg.setRGB(x, y, new Color(redValue,rgb[1],rgb[2], 255).getRGB());
						}
					} else if (color == -7489174) {
						//edges
						if (snow) {
							bufImg.setRGB(x, y, new Color(195,249,255, 255).getRGB());
						} else {
							int[] rgb = getRGB(color);
							bufImg.setRGB(x, y, new Color(redValue+30,rgb[1],rgb[2], 255).getRGB());
						}
					}
				  	
				}
			}
			
			Image image = new Image(BufferedImageUtil.getTexture("", bufImg));
			image.setFilter(Image.FILTER_NEAREST);
			return image;
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
			return null;
		}
		
	}
	
	private int[] getRGB(int color) {
		int[] rgb = new int[3];
		rgb[0]  = (color & 0x00ff0000) >> 16;
	  	rgb[1] = (color & 0x0000ff00) >> 8;
	  	rgb[2]  =  color & 0x000000ff;
	  	return rgb;
	}
	
	protected HashMap<Integer, HashMap<Integer, Image>> downOverlaysMap;
	protected HashMap<Integer, Image> baseMap;
	protected int snowKey = 255;

}
