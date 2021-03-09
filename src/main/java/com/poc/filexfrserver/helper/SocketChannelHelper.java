package com.poc.filexfrserver.helper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

import com.poc.filexfrserver.constant.AppConstants;

public class SocketChannelHelper {

	private static Logger log = Logger.getLogger(SocketChannelHelper.class);

	public SocketChannel createServerSocketChannel() {
		SocketChannel socketChannel = null;
		try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
			serverSocketChannel.socket().bind(new InetSocketAddress(AppConstants.SERVER_PORT));
			log.info("Listening to port " + AppConstants.SERVER_PORT + ", waiting for client connection...");
			socketChannel = serverSocketChannel.accept();
			log.info("Connection established, Address : " + socketChannel.getRemoteAddress());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socketChannel;
	}

}
