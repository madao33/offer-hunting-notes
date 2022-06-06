package com.itheima._05TCP通信二;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

/**
    目标：开发服务器。
         1.注册端口。
         2.接收客户端的Socket管道连接。
         3.从socket通信管道中得到一个字节输入流。
         4.从字节输入流中读取客户端发来的数据。
    ServerSocket类：
        构造器：public ServerSocket(int port)
        方法：public Socket accept()：
             -- 等待接收一个客户端的Socket管道连接请求，连接成功返回一个Socket对象
 */
public class ServerDemo02 {
    public static void main(String[] args) throws Exception {
        System.out.println("----服务端启动----");
        // 1.注册端口: public ServerSocket(int port)
        ServerSocket serverSocket = new ServerSocket(9999);
        // 2.开始等待接收客户端的Socket管道连接。
        Socket socket = serverSocket.accept();
        // 3.从socket通信管道中得到一个字节输入流。
        InputStream is = socket.getInputStream();
        // 4.把字节输入流转换成字符输入流
        Reader isr = new InputStreamReader(is);
        // 5.把字符输入流包装成缓冲字符输入流。
        BufferedReader br = new BufferedReader(isr);
        // 6.按照行读取消息 。
        String line ;
        while((line = br.readLine())!=null){
            System.out.println(line);
        }
    }
}
