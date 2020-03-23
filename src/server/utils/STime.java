package server.utils;

public class STime {
	
	private static long systemTimeDelay;
	private static long lastSystemTime;
	
	/**
	 * Returns the current time in milliseconds.
	 * Adjusts for delays and auto corrects itself.
	 * @return
	 */
	public static synchronized long getCurrent() {
		long systemTime = System.currentTimeMillis();
		if (systemTime < lastSystemTime)
			systemTimeDelay += lastSystemTime - systemTime;
		lastSystemTime = systemTime;
		return systemTime + systemTimeDelay;
	}

}
