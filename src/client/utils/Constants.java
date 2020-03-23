package client.utils;

public class Constants {
	
	public static final int PORT = 43594;
	public static final String IP = "127.0.0.1";
	public static final String NAME = "Seven Guys";
	
	// Indicates that the client will allow us to login as a waiter, manager, kitchen staff or customer.
	public static final boolean DEV_MODE = true;
	
	// Indicates that the client will allow us to login as a waiter, manager or kitchen staff.
	// If false, then client will only allow customer input.
	// DEV MODE overrides this.
	public static final boolean EMPLOYEE_MODE = false;

}
