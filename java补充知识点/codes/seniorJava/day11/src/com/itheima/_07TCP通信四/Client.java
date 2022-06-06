package com.itheima._07TCP通信四;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
/**
 	拓展：（了解）

 	引入：
 		我们之前引入的线程解决一个服务端可以接收多个客户端消息
 		客户端与服务端的线程模型是： N-N的关系。 一个客户端要一个线程。
 		这种模型是不行的，并发越高，系统瘫痪的越快！！

	解决：
 		我们可以在服务端引入线程池，使用线程池来处理与客户端的消息通信！！
 		线程池不会引起出现过多的线程而导致系统死机！！

 	这种方案的优劣势：
 		优势：不会引起系统的死机，可以控制并发线程的数量。
 		劣势：同时可以并发的线程将受到限制。

 */
public class Client {
	public static void main(String[] args) {
		try {
			// 1.客户端要请求于服务端的socket管道连接。
			// Socket(String host, int port)
			Socket socket = new Socket("127.0.0.1" , 9999);
			// 2.从socket通信管道中得到一个字节输出流
			OutputStream os = socket.getOutputStream();
			// 3.把低级的字节输出流包装成高级的打印流。
			PrintStream ps = new PrintStream(os);
			Scanner sc = new Scanner(System.in);
			while(true){
				System.out.print("请说：");
				String msg = sc.nextLine();
				ps.println(msg);
				ps.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
