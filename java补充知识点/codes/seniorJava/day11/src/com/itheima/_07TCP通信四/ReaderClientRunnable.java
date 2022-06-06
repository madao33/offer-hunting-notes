package com.itheima._07TCP通信四;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;

class ReaderClientRunnable implements Runnable {
	private Socket socket ;

	public ReaderClientRunnable(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			// 读取一行数据
			InputStream is = socket.getInputStream() ;
			// 转成一个缓冲字符流
			Reader fr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(fr);
			// 一行一行的读取数据
			String line = null ;
			while((line = br.readLine())!=null){ // 阻塞式的！！
				System.out.println("服务端收到了数据："+line);
			}
		} catch (Exception e) {
			System.out.println("有人下线了");
		}
	}
}





