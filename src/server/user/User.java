package server.user;

import server.network.Session;

public class User {
	
	private int type;
	private Session session;
	
	public User(int type, Session session) {
		this.setType(type);
		this.setSession(session);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public boolean isManager() {
		return type == 0;
	}
	
	public boolean isKitchen() {
		return type == 1;
	}
	
	public boolean isWaiter() {
		return type == 2;
	}
	
	public boolean isCustomer() {
		return session.isCustomer();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
