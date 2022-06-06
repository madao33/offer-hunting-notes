package com.itheima._04字符缓冲流;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
     目标：字符缓冲输出流的使用。

            字节流                              字符流
     字节输入流               字节输出流              字符输入流         字符输出流
     InputStream             OutputStream          Reader            Writer   (抽象类)
     FileInputStream         FileOutputStream      FileReader        FileWriter(实现类)
     BufferedInputStream     BufferedOutputStream  BufferedReader    BufferedWriter(实现类，缓冲流)

     字符缓冲输出流：BufferedWriter
            -- 作用：把字符输出流包装成一个高级的缓冲字符输出流，提高写字符数据的性能。
            -- 构造器：public BufferedWriter(Writer writer):
            -- 原理：高级的字符缓冲输出流多了一个8k的字符缓冲池，写数据性能极大提高了!
            -- 字符缓冲输出流除了提高字符输出流写数据的性能，还多了一个换行的特有功能：
                 public void newLine()：新建一行。
     小结：
        缓冲字符输出流可以把低级的字符输出流进行包装。提高了写字符的性能。
        多了一个换行的功能：public void newLine()：新建一行。
 */
public class BufferedWriterDemo02 {
    public static void main(String[] args) throws Exception {
        // 1.定义一个低级的字符输出流写数据出去
        Writer fw = new FileWriter("Day10Demo/src/dlei07.txt",true);

        // 3.把低级的字符输出流包装成高级的缓冲字符输出流
        BufferedWriter bw = new BufferedWriter(fw);

        // 2.写字符输出
        bw.write("我在黑马学IO流~~~~");
        bw.newLine(); // 换行
        bw.write("我在黑马学IO流~~~~");
        bw.newLine();// 换行

        bw.close();
    }
}