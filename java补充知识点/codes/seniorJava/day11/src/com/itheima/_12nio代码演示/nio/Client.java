package com.itheima._12nio代码演示.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 	目标：NIO通信代码入门演示（了解即可，代码复杂，开发中不会直接使用）

 	NIO通信的三要素：Channel(通道)，Buffer(缓冲池),Selector(多路复用器)。

 */
// NIO模式 非阻塞式 IO 支持高并发的！！
public class Client {
	public static void main(String[] args) {
		try {
			// 1.创建连接服务端的地址
			InetSocketAddress socketAddress =
					new InetSocketAddress("127.0.0.1", 9999);
			// 2.创建一个与服务端的连接通道 ，注意此时并没有连接服务端！！通道！！
			SocketChannel channel = SocketChannel.open() ;
			
			// 3.连接通道 : 让通道与服务端的地址接通 
			channel.connect(socketAddress);
			
			// 4.创建缓冲区，非阻塞，数据是不会直接与服务端通信的，交给缓冲区！
			ByteBuffer buf = ByteBuffer.allocate(1024); // 1kB

			Scanner sc = new Scanner(System.in);
			// 5.循环发送数据给服务端
			while(true){
				// 定义一个字节数组封装用户输入的数据
				// 接收用户的键盘输入，存储到datas中去
				String msg = sc.nextLine();
				byte[] buffer = msg.getBytes();
				// 把数据写给缓冲区
				buf.put(buffer);
				// 复位缓冲区 ,把指针放到第一个位置！！
				buf.flip();
				// 开始写出数据 , 把缓冲区发给了服务端
				channel.write(buf);
				// 清空缓冲区 
				buf.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
