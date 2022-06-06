package com.itheima._01缓冲流的概述和分类;

/**
    目标：缓冲流的概述和分类。

           字节流                                             字符流
    字节输入流               字节输出流                 字符输入流         字符输出流
    InputStream             OutputStream             Reader            Writer   (抽象类)
    FileInputStream         FileOutputStream         FileReader        FileWriter(实现类,低级流，原始流)
    BufferedInputStream     BufferedOutputStream     BufferedReader    BufferedWriter(实现类，缓冲流)

    什么是缓冲流：缓冲流可以提高字节流和字符流的读写数据的性能。
    缓冲流分为四类：
        （1）BufferedInputStream：字节缓冲输入流，可以提高字节输入流读数据的性能。
        （2）BufferedOutStream：  字节缓冲输出流，可以提高字节输出流写数据的性能。
        （3）BufferedReader：  字符缓冲输入流，可以提高字符输入流读数据的性能。
        （4）BufferedWriter：  字符缓冲输出流，可以提高字符输出流写数据的性能。

 */
public class BufferedStreamDemo01 {
    public static void main(String[] args) {

    }
}
