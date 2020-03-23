package server.network.packet.encoder;

import server.network.Session;
import server.network.packet.OutputStream;

public class LoginEncoder extends Encoder {

	public LoginEncoder(Session session) {
		super(session);
	}
	
	public void assignKioskID(int kioskID) {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(2);
		/////////
		stream.writeByte(kioskID);
		////////
		stream.endPacketVarShort();
		session.write(stream);
	}
	
	

}
