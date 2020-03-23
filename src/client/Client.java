package client;
import javax.swing.SwingUtilities;

import client.network.ClientChannel;
import client.network.Session;

public class Client {

	public static Session session;
	public static ClientFrame clientFrame;
	
	public static int tableID;

	public static void main(String[] args) {
		Client.start();
	}
	
	public static void start() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				clientFrame = new ClientFrame();
				clientFrame.setVisible(true);
			}
		});
		
		ClientChannel.openChannel();
	}
	
	public static void restart() {
		clientFrame.dispose();
		ClientChannel.shutdown();
		Client.start();
	}

}
