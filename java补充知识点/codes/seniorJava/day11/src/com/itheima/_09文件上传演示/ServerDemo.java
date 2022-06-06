package com.itheima._09文件上传演示;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
      功能点：
      1.接收多个客户端传输来的图片数据存储到服务器路径：
      2.响应一个成功的消息给当前客户端。
 */
public class ServerDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("----服务端启动----");
        // 1.注册端口: public ServerSocket(int port)
        ServerSocket serverSocket = new ServerSocket(Constants.SERVER_PORT);
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
            // 1.从socket通信管道中得到一个字节输入流读取客户端发来的图片数据！
            InputStream is = socket.getInputStream();
            // 2.包装成高级的缓冲字节输入流
            BufferedInputStream bis = new BufferedInputStream(is);
            // 3.定义一个缓冲字节输出流通向目标路径（服务端路径）
            BufferedOutputStream bos =
                    new BufferedOutputStream(new FileOutputStream(Constants.SERVER_DIR+ UUID.randomUUID().toString()+".jpg"));
            byte[] buffer = new byte[1024];
            int len ;
            while((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0 ,len);
            }
            bos.close();
            System.out.println("服务端接收完毕了！");

            // 4.响应数据给客户端
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("您好，已成功接收您上传的图片！");
            ps.flush();

            Thread.sleep(100000); // 等消失发送完毕被客户端接收后死亡！
        }catch (Exception e){
            System.out.println(socket.getRemoteSocketAddress()+"下线了~~~~~~");
        }
    }
}