package client.network;


import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;

import client.network.packet.Decoder;
import client.network.packet.Encoder;
import client.network.packet.OutputStream;
import client.network.packet.PacketDecoder;
import client.network.packet.PacketEncoder;

public class Session {

	private Channel channel;
	private Decoder decoder;
	private Encoder encoder;

	public Session(Channel channel) {
		this.channel = channel;
		setEncoder(0);
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
	
	public PacketEncoder getPacketEncoder() {
		return (PacketEncoder) encoder;
	}

	public final void setDecoder(int stage) {
		setDecoder(stage, null);
	}

	public final void setDecoder(int stage, Object attachment) {
		switch (stage) {
			case 0:
				decoder = new PacketDecoder(this);
				break;
		}
	}

	public final void setEncoder(int stage) {
		setEncoder(stage, null);
	}

	public final void setEncoder(int stage, Object attachement) {
		switch (stage) {
			case 0:
				encoder = new PacketEncoder(this);
				break;
		}
	}
	
	public String getIP() {
		return channel == null ? "" : channel.getRemoteAddress().toString().split(":")[0].replace("/", "");

	}
	
	public String getLocalAddress() {
		return channel.getLocalAddress().toString();
	}

}
