package com.itheima._03UDP通信;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
     目标：UDP通信的使用介绍(了解即可)

     UDP协议的特点
         * 面向无连接的协议
         * 发送端只管发送，不确认对方是否能收到。
         * 基于数据包进行数据传输。
         * 发送数据的包的大小限制64KB以内
         * 因为面向无连接，速度快，但是不可靠。会丢失数据！

     UDP协议的使用场景
         * 在线视频
         * 网络语音电话

     UDP协议相关的两个类
         * DatagramPacket
             * 数据包对象
             * 作用：用来封装要发送或要接收的数据，比如：集装箱
         * DatagramSocket
             * 发送对象
             * 作用：用来发送或接收数据包，比如：码头
     DatagramPacket类构造器
         发送端用：
            new DatagramPacket(byte[] buf, int length, InetAddress address, int port)
              创建发送端数据包对象
             * buf：要发送的内容，字节数组
             * length：要发送内容的长度，单位是字节
             * address：接收端的IP地址对象
             * port：接收端的端口号

         接收端用：
             new DatagramPacket(byte[] buf, int length)
             * 创建接收端的数据包对象
             * buf：用来存储接收到内容
             * length：能够接收内容的长度

     DatagramPacket类常用方法
         * int getLength() 获得实际接收到的字节个数

     DatagramSocket类构造方法
         * DatagramSocket() 创建发送端的Socket对象，系统会随机分配一个端口号。
         * DatagramSocket(int port) 创建接收端的Socket对象并指定端口号

     DatagramSocket类成员方法
         * void send(DatagramPacket dp) 发送数据包
         * void receive(DatagramPacket p) 接收数据包

     需求：使用UDP实现客户端发，服务端收。(了解)

 */
public class UDPClientDemo01 {
    public static void main(String[] args) throws Exception {
        System.out.println("===启动客户端===");
        // 1.创建一个集装箱对象，用于封装需要发送的数据包!
        /**
         new DatagramPacket(byte[] buf, int length, InetAddress address, int port)
         参数一：封装数据的字节数组。
         参数二：发送数据的长度!
         参数三：服务端的IP地址
         参数四：服务端程序的端口号码。
         */
        byte[] buffer = "今晚，约吗？".getBytes();
        DatagramPacket packet = new DatagramPacket(buffer , buffer.length
                , InetAddress.getLocalHost(),6666);

        // 2.创建一个码头对象
        // 参数可以申明客户端端口，可以有可以没有，默认会给一个端口。
        DatagramSocket socket = new DatagramSocket();

        // 3.开始发送数据包对象
        socket.send(packet);

        socket.close();
    }
}
