package server;

/**
 * Holds global variables to be used across the whole
 * restaurant system.
 * 
 * @author Karimshan
 *
 */
public class Global {
	
	// Holds the list of tables in the restaurant (kiosk ids corresponding
	// to the array's index). Each time a customer connects, they are assigned
	// a random table.
	public static byte[] tableIds;
	
	static {
		tableIds = new byte[20];
		for(int i = 0; i < tableIds.length; i++) {
			tableIds[i] = 0;
		}
	}
}
