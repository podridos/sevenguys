package server.network;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import server.network.packet.OutputStream;
import server.network.packet.decoder.ClientLaunchDecoder;
import server.network.packet.decoder.Decoder;
import server.network.packet.decoder.LoginDecoder;
import server.network.packet.decoder.PacketDecoder;
import server.network.packet.encoder.ClientLaunchEncoder;
import server.network.packet.encoder.Encoder;
import server.network.packet.encoder.LoginEncoder;
import server.network.packet.encoder.PacketEncoder;
import server.user.User;

public class Session {

	private Channel channel;
	private Decoder decoder;
	private Encoder encoder;
	private int tableID;
	private boolean isCustomer;

	public Session(Channel channel) {
		this.channel = channel;	
		setDecoder(0);
	}

	public final ChannelFuture write(OutputStream outStream) {
		if (channel.isConnected()) {
			ChannelBuffer buffer = ChannelBuffers.copiedBuffer(
					outStream.getBuffer(), 0, outStream.getOffset());
			synchronized (channel) {
				return channel.write(buffer);
			}
		}
		return null;
	}

	public final ChannelFuture write(ChannelBuffer outStream) {
		if (outStream == null)
			return null;
		if (channel.isConnected()) {
			synchronized (channel) {
				return channel.write(outStream);
			}
		}
		return null;
	}

	public final Channel getChannel() {
		return channel;
	}

	public final Decoder getDecoder() {
		return decoder;
	}

	public final Encoder getEncoder() {
		return encoder;
	}

	public final void setDecoder(int stage) {
		setDecoder(stage, null);
	}

	public final void setDecoder(int stage, Object attachment) {
		switch (stage) {
			case 0:
				decoder = new ClientLaunchDecoder(this);
				break;
			case 1:
				decoder = new LoginDecoder(this);
				break;
			case 2:
				decoder = new PacketDecoder(this, (User) attachment);
				break;
			case -1:
			default:
				decoder = null;
				break;
		}
	}

	public final void setEncoder(int stage) {
		setEncoder(stage, null);
	}

	public final void setEncoder(int stage, Object attachement) {
		switch (stage) {
			case 0:
				encoder = new ClientLaunchEncoder(this);
				break;
			case 1:
				encoder = new LoginEncoder(this);
				break;
			case 2:
				encoder = new PacketEncoder(this, (User) attachement);
				break;
			case -1:
			default:
				encoder = null;
				break;
		}
	}

	public String getIP() {
		return channel == null ? "" : channel.getRemoteAddress().toString().split(":")[0].replace("/", "");

	}

	public String getLocalAddress() {
		return channel.getLocalAddress().toString();
	}
	
	public int getTableID() {
		return tableID;
	}

	public void setTableID(int i) {
		this.tableID = i;
	}

	public boolean isCustomer() {
		return isCustomer;
	}

	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

}
