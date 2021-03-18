package net.sourceforge.plantuml.test.playground;

public class Logging {

	public static void log(String format, Object... arg) {
		System.out.printf(format, arg);
		System.out.println();
	}

	public static void logException(Exception e, String format, Object... arg) {
		System.out.printf(format, arg);
		System.out.println();
		e.printStackTrace(System.out);
		System.out.println();
	}
}
