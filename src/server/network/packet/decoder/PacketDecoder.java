package server.network.packet.decoder;
import server.network.Session;
import server.network.packet.InputStream;
import server.user.User;

public final class PacketDecoder extends Decoder {
	
	private User user;

	private static final byte[] SIZES = new byte[256];
	
	static {
		loadSizes();
	}

	public static void loadSizes() {
		for (int id = 0; id < SIZES.length; id++)
			SIZES[id] = -4;
		SIZES[1] = 2;
	}

	public PacketDecoder(Session session, User player) {
		super(session);
		this.user = player;
	}

	@Override
	public void decode(InputStream stream) {
		while (stream.getRemaining() > 0 && session.getChannel().isConnected()) {
			//&& !player.hasFinished()) {
			int packetId = stream.readUnsignedByte();
			if (packetId >= SIZES.length) {
				System.out.println("PacketId " + packetId
						+ " has fake packet id.");
				break;
			}

			int length = SIZES[packetId];
			if (length == -1)
				length = stream.readUnsignedByte();
			else if (length == -2)
				length = stream.readUnsignedShort();
			else if (length == -3)
				length = stream.readInt();
			else if (length == -4) {
				length = stream.getRemaining();
				if(packetId != 255)
					System.out.println("Invalid size for PacketId " + packetId
							+ ". Size guessed to be " + length);
			}
			if (length > stream.getRemaining()) {
				length = stream.getRemaining();
				if(packetId != 0)
					System.out.println("PacketId " + packetId
							+ " has fake size. - expected size " + length);
				break;

			}
			
			int startOffset = stream.getOffset();
			
			switch(packetId) {
					
				case 2:
					break;
					
				default:
					break;
			}	
			
			stream.setOffset(startOffset + length);
		}
	}


}
