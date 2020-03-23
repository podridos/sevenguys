package server.network.packet.encoder;
import server.network.Session;
import server.network.packet.OutputStream;
import server.user.User;

public class PacketEncoder extends Encoder {
	
	private User user;

	public PacketEncoder(Session session, User user) {
		super(session);
		this.user = user;
	}
	
}