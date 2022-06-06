package com.itheima._12nio代码演示.nio2;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Scanner;

public class Client {
	private AsynchronousSocketChannel asc ;
	public Client() throws Exception {
		asc = AsynchronousSocketChannel.open();
		asc.connect(new InetSocketAddress("127.0.0.1", 8765));
	}
	public boolean write(String request){
		try {
			asc.write(ByteBuffer.wrap(request.getBytes()));
			read();
			return true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
	}
	private void read() {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			asc.read(buf).get();
			buf.flip();
			byte[] respByte = new byte[buf.remaining()];
			buf.get(respByte);
			System.out.println(new String(respByte,"utf-8").trim());
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		Client c1 = new Client();
		Scanner scanner = new Scanner(System.in);
        while(true){
        	String msg = scanner.nextLine();
			c1.write(msg);
		}
	}
}
