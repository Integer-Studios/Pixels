package com.pixels.input;

public class MouseBinding {
	
	public MouseBinding(String n, int x, int y, int width, int height, int button, MouseBinder m) {
		name = n;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.button = button;
		mouseBinder = m;
	}
	
	private boolean isInArea(int x, int y) {
		return (this.x < x && this.x + this.width > x && this.y < y && this.y + this.height > y);
	}
	
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub

		if (isInArea(x, y)) {
			if (this.button == button) {
				mouseBinder.mouseDown(x, y);
			} else if (this.button == 2 && mouseBinder instanceof MouseBinderFull){
				((MouseBinderFull)mouseBinder).mouseDown(button, x, y);
			}
		}
	}
	public void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub
		if (isInArea(x, y)) {
			if (this.button == button) {
				mouseBinder.mouseUp(x, y);
			} else if (this.button == 2 && mouseBinder instanceof MouseBinderFull){
				((MouseBinderFull)mouseBinder).mouseUp(button, x, y);
			}
		}
	}
	

	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		// TODO Auto-generated method stub
		if (mouseBinder instanceof MouseBinderFull) {
			((MouseBinderFull)mouseBinder).mouseDragged(oldX, oldY, newX, newY);
		}
		
	}
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		// TODO Auto-generated method stub
		if (mouseBinder instanceof MouseBinderFull) {
			((MouseBinderFull)mouseBinder).mouseMoved(oldX, oldY, newX, newY);
		}
		
	}
	public void mouseWheelMoved(int change) {
		// TODO Auto-generated method stub
		if (mouseBinder instanceof MouseBinderFull) {
			((MouseBinderFull)mouseBinder).mouseWheelMoved(change);
		}
		
	}
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		// not used
	}
	
	public String name;
	public int x, y, width, height;
	public MouseBinder mouseBinder;
	public int button;


}
