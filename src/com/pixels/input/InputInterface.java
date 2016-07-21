package com.pixels.input;

import java.util.ArrayList;
import java.util.HashMap;

public class InputInterface {
	
	public HashMap<String, KeyBinding> keyBindings = new HashMap<String, KeyBinding>();
	public HashMap<Integer, MouseBinding> mouseBindings = new HashMap<Integer, MouseBinding>();
	public ArrayList<SimpleMouseListener> simpleListners = new ArrayList<SimpleMouseListener>();
	public int nextBindingIndex = 0;
	
	public InputInterface() {
		
	}
	
	public void clearKeyBindings() {
		keyBindings = new HashMap<String, KeyBinding>();
	}
	
	public void clearMouseBindings() {
		mouseBindings = new HashMap<Integer, MouseBinding>();
	}
	
	public void clearSimpleListners() {
		simpleListners.clear();
	}
	
	public void addKeyBinding(KeyBinding k) {
		keyBindings.put(k.name, k);
	}
	
	public void addMouseBinding(MouseBinding k) {
		k.setID(nextBindingIndex);
		mouseBindings.put(nextBindingIndex, k);
		nextBindingIndex++;
	}
	
	public void addSimpleListner(SimpleMouseListener k) {
		simpleListners.add(k);
	}
	
	public void removeKeyBinding(String name) {
		keyBindings.remove(name);
	}
	
	public void removeMouseBinding(MouseBinding k) {
		mouseBindings.remove(k.getID());
	}
	
	public void removeSimpleListner(int i) {
		simpleListners.remove(i);
	}
	
	public void mousePressed(int button, int x, int y) {
		for (int i = 0; i < mouseBindings.size(); i++) {
			mouseDown(x, y, ((MouseBinding) mouseBindings.values().toArray()[i]));
		}
		for (SimpleMouseListener s : simpleListners) {
			s.mouseDown(button, x, y);
		}
	}
	
	public void mouseReleased(int button, int x, int y) {
		for (int i = 0; i < mouseBindings.size(); i++) {
			mouseUp(x, y, ((MouseBinding) mouseBindings.values().toArray()[i]));
		}
		for (SimpleMouseListener s : simpleListners) {
			s.mouseUp(button, x, y);
		}
	}
	
	public void keyPressed(int code, char v) {
		for (int i = 0; i < keyBindings.size(); i++) {
			((KeyBinding) keyBindings.values().toArray()[i]).onKeyDown(code);
		}
	}
	
	public void keyReleased(int code, char v) {
		for (int i = 0; i < keyBindings.size(); i++) {
			((KeyBinding) keyBindings.values().toArray()[i]).onKeyUp(code);
		}
	}
	
	private static boolean isInArea(int x, int y, MouseBinding k) {
		return (k.getX() < x && k.getX() + k.getWidth() > x && k.getY() < y && k.getY() + k.getHeight() > y);
	}
	
	private static void mouseDown(int x, int y, MouseBinding k) {
		if (isInArea(x, y, k))
			k.mouseDown();
	}
	
	private static void mouseUp(int x, int y, MouseBinding k) {
		if (isInArea(x, y, k))
			k.mouseUp();
	}

}
