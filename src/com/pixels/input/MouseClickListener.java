package com.pixels.input;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;


public class MouseClickListener implements MouseListener {

	public static HashMap<String, MouseBinding> mouseBindings = new HashMap<String, MouseBinding>();
	public static ArrayList<SimpleMouseListener> simpleListners = new ArrayList<SimpleMouseListener>();
	
	public static void clearAll() {
		clearMouseBindings();
		clearSimpleListners();
	}
	
	public static void clearMouseBindings() {
		mouseBindings = new HashMap<String, MouseBinding>();
	}
	
	public static void clearSimpleListners() {
		simpleListners.clear();
	}
	
	public static void addMouseBinding(MouseBinding k) {
		mouseBindings.put(k.name, k);
	}
	
	public static void addSimpleListner(SimpleMouseListener k) {
		simpleListners.add(k);
	}
	
	public static void removeMouseBinding(String name) {
		mouseBindings.remove(name);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mouseBindings.size(); i++) {
			((MouseBinding) mouseBindings.values().toArray()[i]).mouseClicked(button, x, y, clickCount);
		}

	}

	@Override
	public void mouseDragged(int oldX, int oldY, int newX, int newY) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mouseBindings.size(); i++) {
			((MouseBinding) mouseBindings.values().toArray()[i]).mouseDragged(oldX, oldY, newX, newY);
		}

	}

	@Override
	public void mouseMoved(int oldX, int oldY, int newX, int newY) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mouseBindings.size(); i++) {
			((MouseBinding) mouseBindings.values().toArray()[i]).mouseMoved(oldX, oldY, newX, newY);
		}

	}

	@Override
	public void mousePressed(int button, int x, int y) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mouseBindings.size(); i++) {
			((MouseBinding) mouseBindings.values().toArray()[i]).mousePressed(button, x, y);
		}
		for (SimpleMouseListener s : simpleListners) {
			s.mouseDown(button, x, y);
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mouseBindings.size(); i++) {
			((MouseBinding) mouseBindings.values().toArray()[i]).mouseReleased(button, x, y);
		}
		for (SimpleMouseListener s : simpleListners) {
			s.mouseUp(button, x, y);
		}
	}

	@Override
	public void mouseWheelMoved(int change) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mouseBindings.size(); i++) {
			((MouseBinding) mouseBindings.values().toArray()[i]).mouseWheelMoved(change);
		}
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
