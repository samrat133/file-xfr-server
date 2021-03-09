package com.poc.filexfrserver.receiver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

import org.apache.log4j.Logger;

import com.poc.filexfrserver.constant.AppConstants;
import com.poc.filexfrserver.util.AppUtil;

public class Receiver {

	private static Logger log = Logger.getLogger(Receiver.class);
	int counter;

	/**
	 * Reads the bytes from socket and writes to file
	 *
	 * @param socketChannel
	 */
	public void readFileFromSocket(SocketChannel socketChannel) {

		String fileName = AppUtil.getFileName();
		try (RandomAccessFile aFile = new RandomAccessFile(fileName, AppConstants.FILE_MODE)) {
			ByteBuffer buffer = ByteBuffer.allocate(8192);
			FileChannel fileChannel = aFile.getChannel();
			while (socketChannel.read(buffer) > 0) {
				buffer.flip();
				fileChannel.write(buffer);
				buffer.clear();
				counter++;
				if (counter % 8192 == 0)
					System.out.print(".");
			}
			fileChannel.close();
			log.info("File transfer completed. Received file : " + fileName);
			socketChannel.close();
		} catch (FileNotFoundException fileNotFound) {
			log.error("Exception occurred while writing file : " + fileNotFound.getMessage(), fileNotFound);
		} catch (IOException iOException) {
			log.error("Exception occurred while writing file : " + iOException.getMessage(), iOException);
		}

	}

}
