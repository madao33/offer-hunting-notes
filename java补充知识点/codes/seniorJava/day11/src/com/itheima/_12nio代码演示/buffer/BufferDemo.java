package com.itheima._12nio代码演示.buffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
    目标：NIO中的缓冲池Buffer的使用介绍！

    NIO是基于Buffer缓冲池进行数据的读写操作。
 */
public class BufferDemo {
    public static void main(String[] args) throws Exception {
        //声明源文件和目标文件
        FileInputStream is = new FileInputStream(new File("Day11Demo/dlei.txt"));
        FileOutputStream os = new FileOutputStream(new File("Day11Demo/new.txt"));
        //获得传输通道channel
        FileChannel inChannel = is.getChannel();
        FileChannel outChannel = os.getChannel();
        // 获得容器buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(true){
            //判断是否读完文件
            int eof = inChannel.read(buffer);
            if(eof==-1){
                break;
            }
            //重设一下buffer的 position=0,让指针恢复到第一个位置！！
            buffer.flip();
            //开始写
            outChannel.write(buffer);
            // 写完要重置buffer，重设,让指针恢复到第一个位置！！
            buffer.clear();
        }
        inChannel.close();
        outChannel.close();
        is.close();
        os.close();
    }
}
