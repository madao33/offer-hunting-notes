package com.itheima._03UDP通信;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 目标：UDP服务端开发。接收客户端的消息。
 */
public class UDPServerDemo02 {
    public static void main(String[] args) throws Exception {
        System.out.println("==启动服务端程序==");
        // 1.创建一个接收客户都端的数据包对象（集装箱）
        /**
         * new DatagramPacket(byte[] buffer ,int lenght):
         * 参数一：接收数据的数组。
         * 参数二：接收数据的数组的长度！
         */
        byte[] buffer = new byte[1024*64];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        // 2.创建一个接收端的码头对象
        DatagramSocket socket = new DatagramSocket(6666);

        // 3.开始接收
        socket.receive(packet);

        // 4.从集装箱中获取本次读取的数据量
        int len = packet.getLength();

        // 5.输出数据
        String rs = new String(buffer , 0 , len);
        System.out.println(rs);

        // 6.服务端还可以获取发来信息的客户端的IP和端口。
        String ip = packet.getAddress().getHostAddress();
        int port = packet.getPort();
        System.out.println("对方："+ip+":"+port);
        socket.close();
    }
}
