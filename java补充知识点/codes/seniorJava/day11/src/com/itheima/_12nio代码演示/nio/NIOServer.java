package com.itheima._12nio代码演示.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO服务端:
 * 		1.接收无数个客户端的连接。
 * 		2.得到一个多路复用器对象去轮询所有的客户端管道。
 * 		3.发现该管道有数据过来触发读取，没有就继续轮询下一个客户端管道。
 */
public class NIOServer {
	
	// 1.创建通道的管理器 : 多路复用器（轮询客户端的关键对象！）
	// 管理多个客户端通道
	private Selector selector ;
	private ServerSocketChannel channel; // 负责接收客户端的连接等！

	// 创建好多路复用器对象
	public NIOServer(int port) {
		try {
			// 打开通道管理器，创建一个多路复用器，管理通道 
			// 创建一个多路复用器
			this.selector = Selector.open();
			// 2.创建一个服务器的通道用于接收客户端的连接 
			// ServerSocketChannel 这个对象是接收客户端的
			channel = ServerSocketChannel.open() ;
			// 绑定服务端的通道端口
			channel.bind(new InetSocketAddress(9999));
			// 3.设置通道为非阻塞 
			channel.configureBlocking(false);
			// 4.让多路复用器开始负责使用管道接收客户端的连接请求 -> 然后管理通道
			// 一个线程：多路复用器：selector
			// 一个线程：管道channel
			channel.register(selector,  SelectionKey.OP_ACCEPT);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listen() {
		try {
			// 多路复用器，开始轮询客户端通道，看是否有事件需要处理！
			while(true){
				System.out.println("轮询了一次~~~");
				//1 必须要让多路复用器开始监听
				// 开始轮询
				this.selector.select();
				//2.通过多路选择器轮询所有的客户端事件状态
				// 提取所有通道的状态
				Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
				//3.遍历这些状态   [c1 ,c2 ,c3 ,c4.....]
				while(keys.hasNext()){
					System.out.println("事件处理了一次~~~");
					// 该通道的事件对象！！
					SelectionKey key = keys.next() ;
					// 删除即将处理的key，以防止被重复处理 
					keys.remove();
					// 开始处理状态 
					if(key.isAcceptable()){
						// 客户端发来了连接请求
						// 处理与客户端的连接
						handlerAccept(key);
					}else if(key.isReadable()){
						// 获得了可读事件
						handlerReader(key);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("在轮询是出现异常！");
		}
		
	}

	private void handlerAccept(SelectionKey key) {
		try {
			// 1.处理与客户端通道的连接
			// 获取服务端通道
			ServerSocketChannel ss = (ServerSocketChannel) key.channel();
			// 接收客户端的请求
			SocketChannel channel = ss.accept();
			// 设置非阻塞模式
			channel.configureBlocking(false);
			// 注册到多路通道上去(申明这个管道是可读了！)
			channel.register(selector, SelectionKey.OP_READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handlerReader(SelectionKey key) {
		try {
			// 获取通道对象
			SocketChannel channel = (SocketChannel) key.channel();
			// 定义缓冲区读取数据 
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			// 读取通道的数据放置到缓冲区中去 
			int count = channel.read(buffer);
			// 判断读取的结果 
			if(count == -1 ){
				// 说明没有数据可读
				key.channel().close();
				key.cancel();
				System.out.println("通道关闭了一个客户端！！");
				return ;
			}
			//5 有数据则进行读取 读取之前需要进行复位方法(把position 和limit进行复位)
			buffer.flip();
			//6 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
			byte[] bytes = new byte[buffer.remaining()];
			//7 接收缓冲区数据
			buffer.get(bytes);
			//8 打印结果
			String body = new String(bytes).trim();
			System.out.println("Server : " + body);
		} catch (Exception e) {
			try {
				// 从服务端移除该客户端的通道对象
				key.channel().close();
				key.cancel();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		// 初始化服务端:只是做好了多路复用器：管理客户端通道的Selector对象！！
		NIOServer server = new NIOServer(9999);
		// 监听轮询客户端！！ 深刻理解NIO
		server.listen();
	}
}
