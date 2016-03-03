package com.pixel.input;

public class KeyBinding {
	
	public KeyBinding(String n, int c, KeyBinder k) {
		name = n;
		code = c;
		keyBinder = k;
	}
	
	public void onKeyDown(int c) {
		if (code == c) {
			keyBinder.onKeyDown(name);
		}
	}
	
	public void onKeyUp(int c) {
		if (code == c) {
			keyBinder.onKeyUp(name);
		}
	}
	
	public String name;
	public int code;
	public KeyBinder keyBinder;

}
