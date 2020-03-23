package server.network.packet.decoder;

import server.network.Session;
import server.network.packet.InputStream;

public abstract class Decoder {

	protected Session session;

	public Decoder(Session session) {
		this.session = session;
	}

	public abstract void decode(InputStream stream);

}
