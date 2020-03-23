package client.network.packet;
import client.Client;
import client.network.Session;

public final class PacketDecoder extends Decoder {
	
	private static final byte[] SIZES = new byte[256];
	
	static {
		loadSizes();
	}

	public static void loadSizes() {
		for (int id = 0; id < SIZES.length; id++)
			SIZES[id] = -4;
		SIZES[1] = 2;
	}

	public PacketDecoder(Session session) {
		super(session);
	}

	@Override
	public void decode(InputStream stream) {
		while (stream.getRemaining() > 0 && session.getChannel().isConnected()) {
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
			
			stream.skip(2);
			switch(packetId) {
			
				// First acknowledgement from server.
				case 1:
					session.getPacketEncoder().sendSecondHandshake();
					System.out.println("Sent second handshake");
					break;
					
				// Customer/table gets assigned a table ID by the server.
				case 2:
					Client.tableID = stream.readUnsignedByte();
					System.out.println("Assigned table ID "+Client.tableID);
					break;
					
				default:
					break;
			}
			
			stream.setOffset(startOffset + length);
		}
	}

	
}
