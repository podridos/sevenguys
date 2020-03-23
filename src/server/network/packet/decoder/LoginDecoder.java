package server.network.packet.decoder;

import server.Global;
import server.network.Session;
import server.network.packet.InputStream;
import server.network.packet.encoder.ClientLaunchEncoder;
import server.network.packet.encoder.LoginEncoder;
import server.user.User;

public class LoginDecoder extends Decoder {

	public LoginDecoder(Session session) {
		super(session);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decode(InputStream stream) {
		int packetId = stream.readUnsignedByte();
		stream.skip(2);
		switch (packetId) {
			// Second handshake from client to set login encoder.
			case 2:
				System.out.println("Received second handshake from client session.");
				session.setEncoder(1);
				break;
			// Acknowledges that a customer has connected to the client and 
			// assigns them a free table.
			case 3:
				if(session.getEncoder() instanceof LoginEncoder) {
					for(int i = 0; i < Global.tableIds.length; i++) {
						if(Global.tableIds[i] == 0) {
							Global.tableIds[i] = 1;
							session.setTableID(i);
							session.setCustomer(true);
							((LoginEncoder) session.getEncoder()).assignKioskID(i);
							break;
						}
					}
				}
				break;
			// Help request from client table
			case 4:
				int kioskID = stream.readUnsignedByte();
				System.out.println("Help requested from table: "+kioskID);
				/*
				for(User user : Global.getAllUsers()) {
					if(user.isWaiter() || user.isManager()) {
						user.getSession().getPacketEncoder().updateTable(kioskID);
					}
				}
				*/
				break;
		}
	}

}
