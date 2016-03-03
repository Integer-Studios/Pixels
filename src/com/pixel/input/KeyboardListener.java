package com.pixel.input;

import java.util.HashMap;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class KeyboardListener implements KeyListener {
	
	public static HashMap<String, KeyBinding> keyBindings = new HashMap<String, KeyBinding>();
	
	public static void clearKeyBindings() {
		keyBindings = new HashMap<String, KeyBinding>();
	}
	
	public static void addKeyBinding(KeyBinding k) {
		keyBindings.put(k.name, k);
	}
	
	public static void removeKeyBinding(String name) {
		keyBindings.remove(name);
	}

	@Override
	public void keyPressed(int code, char v) {
		// TODO Auto-generated method stub
		for (int i = 0; i < keyBindings.size(); i++) {
			((KeyBinding) keyBindings.values().toArray()[i]).onKeyDown(code);
		}
	}
	
	@Override
	public void keyReleased(int code, char v) {
		// TODO Auto-generated method stub
		for (int i = 0; i < keyBindings.size(); i++) {
			((KeyBinding) keyBindings.values().toArray()[i]).onKeyUp(code);
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
