package com.pixels.util;

public class Log {
	
	public static void print(ThreadName t, Object o) {
		if (t == ThreadName.MAIN && main) {
			System.out.println("[Pixel Realms Client] (main) " + o);
		}
		if (t == ThreadName.CLIENT && client) {
			System.out.println("[Pixel Realms Client] (client) " + o);
		}
	}
	
	public static void error(ThreadName t, Object o) {
		if (t == ThreadName.MAIN && main) {
			System.out.println("[ERROR] (main)" + o);
		}
		if (t == ThreadName.CLIENT && client) {
			System.out.println("[ERROR] (client)" + o);
		}
	}
	
	public static boolean main = true;
	public static boolean client = true;

}
