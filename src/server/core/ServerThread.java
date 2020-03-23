package server.core;
import server.utils.STime;

public final class ServerThread extends Thread {
	
	public static long lastCycleTime;

	protected ServerThread() {
		setPriority(Thread.MAX_PRIORITY);
		setName("Server Thread");
	}

	@Override
	public final void run() {
		while (!CoresManager.shutdown) {
			long currentTime = STime.getCurrent();
			try {
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			lastCycleTime = STime.getCurrent();
			// The server will cycle every 400 milliseconds to quickly receive 
			// updates from the clients that are connected to it.
			long sleepingTime = 400 + currentTime - lastCycleTime;
			if (sleepingTime <= 0)
				continue;
			try {
				Thread.sleep(sleepingTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
