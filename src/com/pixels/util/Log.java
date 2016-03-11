package com.pixels.util;

public class Log {
	
	public static void print(ThreadName t, Object o) {
		if (t == ThreadName.MAIN && main) {
			System.out.println("[Pixel Realms Client] "+count+" (main) " + o);
		}
		if (t == ThreadName.CLIENT && client) {
			System.out.println("[Pixel Realms Client] "+count+" (client) " + o);
		}
		count++;
	}
	
	public static void error(ThreadName t, Object o) {
		if (t == ThreadName.MAIN && main) {
			System.out.println("[ERROR] "+count+" (main)" + o);
		}
		if (t == ThreadName.CLIENT && client) {
			System.out.println("[ERROR] "+count+" (client)" + o);
		}
		count++;
	}
	
	public static boolean main = true;
	public static boolean client = true;
	private static int count = 0;

}
