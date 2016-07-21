package com.pixels.input;

import java.util.HashMap;

public class InterfaceManager {
	
	private static HashMap<String, InputInterface> interfaces = new HashMap<String, InputInterface>();
	private static String currentInterface = "";
	public static InputInterface guiInterface;
	public static InputInterface worldInterface;
	
	public static void initializeInterfaces() {
		guiInterface = new InputInterface();
		worldInterface = new InputInterface();
		InterfaceManager.addInterface("gui", guiInterface);
		InterfaceManager.addInterface("world", worldInterface);
		InterfaceManager.setCurrentInterface("world");
	}
	
	public static void addInterface(String s, InputInterface i) {
		interfaces.put(s, i);
	}
	
	public static void removeInterface(String s) {
		interfaces.remove(s);
	}
	
	public static void clearInterfaces() {
		interfaces = new HashMap<String, InputInterface>();
	}
	
	public static InputInterface getCurrentInterface() {
		return interfaces.get(currentInterface);
	}
	
	public static void setCurrentInterface(String s) {
		currentInterface = s;
	}

}
