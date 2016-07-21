package com.pixels.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;


public class MouseClickListener implements MouseListener {

	private static int curMouseX, curMouseY;

	public static int getX() {
		return curMouseX;
	}
	
	public static int getY() {
		return curMouseY;
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		// TODO Auto-generated method stub
		curMouseX = newX;
		curMouseY = newY;
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		InterfaceManager.getCurrentInterface().mousePressed(button, x, y);
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		InterfaceManager.getCurrentInterface().mouseReleased(button, x, y);
	}

	@Override
	public void mouseWheelMoved(int change) {
		// TODO Auto-generated method stub

	}
	
	
	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub
		
	}

}
