package com.poc.filexfrserver;

import java.nio.channels.SocketChannel;

import com.poc.filexfrserver.helper.SocketChannelHelper;
import com.poc.filexfrserver.receiver.Receiver;

public class FileXfrServer {

	public static void main(String[] args) {
		SocketChannelHelper socketChannelHelper = new SocketChannelHelper();
		Receiver fileReceiver = new Receiver();
		while (true) {
			SocketChannel socketChannel = socketChannelHelper.createServerSocketChannel();
			fileReceiver.readFileFromSocket(socketChannel);
		}
	}
}