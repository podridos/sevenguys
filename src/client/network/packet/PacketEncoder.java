package client.network.packet;
import client.Client;
import client.network.Session;

public class PacketEncoder extends Encoder {
	
	public PacketEncoder(Session session) {
		super(session);
		sendHandshake();
	}
	
	private void sendHandshake() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(1);
		stream.endPacketVarShort();
		session.write(stream);
		session.setDecoder(0);
	}
	
	public void sendSecondHandshake() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(2);
		stream.endPacketVarShort();
		session.write(stream);
	}

	/**
	 * This lets the server portion know that a customer 
	 * has connected to the kiosk and assigns them a table/kiosk ID.
	 */
	public void sendCustomerConnected() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(3);
		stream.endPacketVarShort();
		session.write(stream);
	}
	
	/**
	 * Sends the server a help request with its
	 * kiosk number.
	 */
	public void sendHelpRequest() {
		OutputStream stream = new OutputStream();
		stream.writePacketVarShort(4);
		stream.writeByte(Client.tableID);
		stream.endPacketVarShort();
		session.write(stream);
	}
	
}