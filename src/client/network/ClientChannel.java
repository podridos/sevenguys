package client.network;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import client.Client;
import client.network.packet.InputStream;
import client.utils.Constants;

public class ClientChannel extends SimpleChannelHandler {
	
	private static ChannelGroup channels;
	private static ChannelFactory factory;
	private static ClientBootstrap bootstrap;
	private static ChannelFuture future;

	public static final void openChannel() {
		new ClientChannel();
	}

	public ClientChannel() {
		channels = new DefaultChannelGroup();
		factory = new NioClientSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());

		/*
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() {
                return Channels.pipeline(
                    new DelimiterBasedFrameDecoder(500, Delimiters.lineDelimiter()),
                    new Client4());
            }
        });
		 */
		
		bootstrap = new ClientBootstrap(factory);
		bootstrap.getPipeline().addLast("handler", this);
		bootstrap.setOption("tcpNoDelay", true);
		bootstrap.setOption("TcpAckFrequency", true);
		bootstrap.setOption("keepAlive", true);

		future = bootstrap.connect(new InetSocketAddress(Constants.IP, Constants.PORT));
		
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
		if (!(e.getMessage() instanceof ChannelBuffer))
			return;
		Object sessionObject = ctx.getAttachment();
		if (sessionObject != null && sessionObject instanceof Session) {
			Session session = (Session) sessionObject;
			if (session.getDecoder() == null)
				return;
			ChannelBuffer buf = (ChannelBuffer) e.getMessage();
			buf.markReaderIndex();
			int avail = buf.readableBytes();
			if (avail < 1 || avail > 7500)
				return;
			byte[] buffer = new byte[avail];
			buf.readBytes(buffer);
			try {
				session.getDecoder().decode(new InputStream(buffer));
			} catch (Throwable er) {
				er.printStackTrace();
			}
		}
	}
	
	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) {
		channels.add(e.getChannel());
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
		Session session = new Session(e.getChannel());
		Client.session = session;
		ctx.setAttachment(session);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
		e.getCause().printStackTrace();
		e.getChannel().close();
	}

	public static final void shutdown() {
		channels.close().awaitUninterruptibly();
		bootstrap.releaseExternalResources();
		future.awaitUninterruptibly();
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		factory.releaseExternalResources();
	}

}


