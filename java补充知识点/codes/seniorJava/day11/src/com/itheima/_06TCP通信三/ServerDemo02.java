package com.itheima._06TCP通信三;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
    目标：开发服务器。
         1.注册端口。
         2.接收客户端的Socket管道连接。
         3.从socket通信管道中得到一个字节输入流。
         4.从字节输入流中读取客户端发来的数据。
 */
public class ServerDemo02 {
    public static void main(String[] args) throws Exception {
        System.out.println("----服务端启动----");
        // 1.注册端口: public ServerSocket(int port)
        ServerSocket serverSocket = new ServerSocket(9999);
        // 2.定义一个循环不断的接收客户端的连接请求
        while(true){
            // 3.开始等待接收客户端的Socket管道连接。
            Socket socket = serverSocket.accept();
            // 4.每接收到一个客户端必须为这个客户端管道分配一个独立的线程来处理与之通信。
            new ServerReaderThread(socket).start();
        }
    }
}

class ServerReaderThread extends Thread{
    private Socket socket ;
    public ServerReaderThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            // 3.从socket通信管道中得到一个字节输入流。
            InputStream is = socket.getInputStream();
            // 4.把字节输入流转换成字符输入流
            Reader isr = new InputStreamReader(is);
            // 5.把字符输入流包装成缓冲字符输入流。
            BufferedReader br = new BufferedReader(isr);
            // 6.按照行读取消息 。
            String line ;
            while((line = br.readLine())!=null){
                System.out.println(socket.getRemoteSocketAddress()+"说："+line);
            }
        }catch (Exception e){
            System.out.println(socket.getRemoteSocketAddress()+"下线了~~~~~~");
        }
    }
}