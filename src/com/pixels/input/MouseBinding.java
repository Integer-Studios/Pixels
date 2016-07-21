package com.pixels.input;

public interface MouseBinding {
	
	public int getX();
	public int getY();
	public int getWidth();
	public int getHeight();
	public int getButton();
	public int getID();
	public void setID(int i);
	
	public void mouseDown();
	public void mouseUp();

}
