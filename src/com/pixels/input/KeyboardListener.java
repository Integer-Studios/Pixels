package com.pixels.input;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class KeyboardListener implements KeyListener {

	@Override
	public void keyPressed(int code, char v) {
		InterfaceManager.getCurrentInterface().keyPressed(code, v);
	}
	
	@Override
	public void keyReleased(int code, char v) {
		InterfaceManager.getCurrentInterface().keyReleased(code, v);
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
