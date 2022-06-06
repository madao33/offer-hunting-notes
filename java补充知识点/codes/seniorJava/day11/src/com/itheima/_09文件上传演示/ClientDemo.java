package com.itheima._09文件上传演示;

import java.io.*;
import java.net.Socket;

/**
     目标：实现客户端上传图片给服务端保存起来。

     开发客户端：本地图片:  ‪D:\itcast\图片资源\beautiful.jpg
     开发服务端：服务器路径： D:\itcast\约吧图片服务器
 */
public class ClientDemo {
    // 本地图片路径、
    public static void main(String[] args) throws Exception {
        // 1.请求于服务端的Socket管道连接。
        Socket socket = new Socket(Constants.SERVER_IP , Constants.SERVER_PORT);
        // 2.从socket管道中得到一个字节输出流包装成缓冲字节输出流
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        // 3.提取本机的图片上传给服务端
        // 4.得到一个缓冲字节输入流与本地图片接通
        BufferedInputStream bis =
                new BufferedInputStream(new FileInputStream(Constants.SRC_IMAGE));
        // 5.定义一个字节数组
        byte[] buffer = new byte[1024];
        int len ;
        while((len = bis.read(buffer)) != -1) {
            bos.write(buffer, 0 ,len);
        }
        bos.flush(); // 刷新图片数据到服务端！！
        socket.shutdownOutput(); // 告诉服务端我的数据已经发送完毕，请不要在等我了！
        bis.close(); // 可以关闭

        // 6.等待着服务端的响应数据！！
        BufferedReader  br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("收到服务端响应："+br.readLine());
    }
}
