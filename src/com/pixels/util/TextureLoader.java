package com.pixels.util;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.InputStream;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TextureLoader {
    
	public static final String RESET = "\u001B[0m";
	public static String red_color = "\u001B[31m";
	public static PathFinder pathFinder = new PathFinder();
	public static Font font = getCustomFont();
	public static int displayWidth, displayHeight;
	
	public static Image load(String path) {
		
		Image image = loadImage(pathFinder.find() + "resources" + path);
		image.setFilter(Image.FILTER_NEAREST);
		
		return image;
		
	}

	public static Image loadImage(String path) {
		
		try {
				
			if (path.length() > 0 && path.substring(0,path.lastIndexOf("/")).length() > 0) {
				 try {
					 
					 return new Image(path);
		        } catch (SlickException e) {
		     
		        }
			}
		} catch (RuntimeException e) {
        	e.printStackTrace();
        	System.err.println("Failed to load image. Skipping.");
        }
       return null;
    }


	public static Font getCustomFont() {
		
		Font font;
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);

		try {
			InputStream inputStream	= new FileInputStream(pathFinder.find() + "resources/fonts/MixSerifCondense.ttf");

			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			font = awtFont2;
		} catch (Exception e) {
			font = awtFont;
			e.printStackTrace();
		}	
		
		return font;
	}
	
	public static void init() {
		
		displayWidth = Display.getWidth();
		displayHeight = Display.getHeight();

	}

	public static void render(GameContainer c, Graphics g) {
			
		displayWidth = Display.getWidth();
		displayHeight = Display.getHeight();

	}
	
}

class PathFinder {
	
	public String find() {
		String path = getRunPath();
		path = shortEndPath(path);
	
		path = path.substring(0, path.lastIndexOf("/") + 1);
	
		return path;
	}
	
	public String getRunPath() {
		String makepath = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
		makepath = makepath.substring(5);
		makepath = makepath.replaceAll("%20", " ");
		if (makepath.endsWith(".jar")) {
			makepath += "/";
		}
		return makepath;
	}

	public boolean pathIsShortEnd(String path) {
		return path.charAt(path.length()-1) != '/';
	}
	
	public String shortEndPath(String path) {
		if (!pathIsShortEnd(path))
			return path.substring(0, path.length()-1);
		return path;
	}
	
}
