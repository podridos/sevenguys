package server.network.packet.decoder;

import server.network.Session;
import server.network.packet.InputStream;

public class ClientLaunchDecoder extends Decoder {

	public ClientLaunchDecoder(Session session) {
		super(session);
		
	}

	@Override
	public void decode(InputStream stream) {
		int packetId = stream.readUnsignedByte();
		System.out.println("packet received from client: "+packetId);
		stream.skip(2);
		switch (packetId) {
			// Handshake
			case 1:
				// Ready to receive login/guest login requests from the client.
				session.setDecoder(1);
				// Sends an acknowledgement back to the client.
				session.setEncoder(0);
				break;
		}
	}
	
	

}
