package com.itheima._05字符流不同编码读取乱码的问题;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

/**
    目标：字符流不同编码读取乱码的问题。
    引入：
        我们之前用的代码编码和文件编码都是UTF-8编码，字符流读取没有出现乱码!!
        字符流读取：
            代码编码            文件编码         中文情况。
            UTF-8              UTF-8           不乱码!
            GBK                GBK             不乱码!
            UTF-8              GBK             乱码!
    小结：
        如果代码编码和读取的文件编码一致。字符流读取的时候不会乱码。
        如果代码编码和读取的文件编码不一致。字符流读取的时候会乱码。
 */
public class ReaderDemo {
    public static void main(String[] args) throws Exception {
        // 1.定义一个原始的字符输入流读取源文件
        //  代码UTF-8  文件UTF-8 不会出现乱码！
        // Reader fr = new FileReader("Day10Demo/src/dlei06.txt");
        //  代码UTF-8  文件GBK   会出现乱码！
        Reader fr = new FileReader("D:\\itcast\\网络编程公开课\\Netty.txt");
        // 2.把低级的字符输入流管道包装成一个高级的缓冲字符输入流管道
        BufferedReader br = new BufferedReader(fr);
        // 3.定义一个字符串变量存储每行数据
        String line;
        // 使用一个循环读取数据(经典代码)
        while((line = br.readLine())!=null){
            System.out.println(line);
        }
    }
}
