package com.pixels.gui;

import com.pixels.input.InterfaceManager;
import com.pixels.input.MouseBinding;

public class GUIClickable extends GUIComponent implements MouseBinding {

	public GUIClickable(int x, int y) {
		
		super(x, y);
		InterfaceManager.guiInterface.addMouseBinding(this);

	}
	
	public GUIClickable(int x, int y, int w, int h, String t) {
		
		super(x,y,w,h,t);
		InterfaceManager.guiInterface.addMouseBinding(this);
		
	}
	
	public GUIClickable(int x, int y, GUIComponent[] c) {
		
		super(x,y,c);
		InterfaceManager.guiInterface.addMouseBinding(this);
		
	}
	
	public GUIClickable(int x, int y, int w, int h, String t, GUIComponent[] c) {
		
		super(x,y,w,h,t,c);
		InterfaceManager.guiInterface.addMouseBinding(this);
		
	}

	@Override
	public int getX() {
		if (parent != null)
			return parent.getBaseX() + x;
		else
			return x;
	}

	@Override
	public int getY() {
		if (parent != null)
			return parent.getBaseY() + y;
		else
			return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getButton() {
		return 0;
	}

	@Override
	public int getID() {
		return bindingID;
	}

	@Override
	public void setID(int i) {
		bindingID = i;
	}

	@Override
	public void mouseDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseUp() {
		// TODO Auto-generated method stub
		
	}

	private int bindingID = -1;
}
