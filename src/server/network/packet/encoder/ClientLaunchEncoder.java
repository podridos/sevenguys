package server.network.packet.encoder;

import server.network.Session;
import server.network.packet.OutputStream;

public class ClientLaunchEncoder extends Encoder {

	public ClientLaunchEncoder(Session session) {
		super(session);
		sendAck();
	}
	
	private void sendAck() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(1);
		stream.endPacketVarShort();
		session.write(stream);
	}

}
