package com.itheima._06转换流;
import java.io.*;
/**
     目标：字符输出转换OutputStreamWriter流的使用。

                字节流                                         字符流
     字节输入流               字节输出流              字符输入流            字符输出流
     InputStream             OutputStream          Reader               Writer   (抽象类)
     FileInputStream         FileOutputStream      FileReader           FileWriter(实现类)
     BufferedInputStream     BufferedOutputStream  BufferedReader       BufferedWriter(实现类，缓冲流)
                                                   InputStreamReader    OutputStreamWriter
     字符输出转换流：OutputStreamWriter
           -- 作用：可以指定编码把字节输出流转换成字符输出流。
                   可以指定写出去的字符的编码。
           -- 构造器：
                public OutputStreamWriter(OutputStream os) :   用当前默认编码UTF-8把字节输出流转换成字符输出流
                public OutputStreamWriter(OutputStream os , String charset):指定编码把字节输出流转换成字符输出流
     小结：
        字符输出转换流可以指定编码把字节输出流转换成字符输出流。
        从而实现指定写出去的字符编码！
 */
public class OutputStreamWriterDemo02 {
    public static void main(String[] args) throws Exception {
        // 1.写一个字节输出流通向文件
        OutputStream os = new FileOutputStream("Day10Demo/src/dlei07.txt");

        // 2.把字节输出流转换成字符输出流。
        // Writer fw = new OutputStreamWriter(os); // .把字节输出流按照默认编码UTF-8转换成字符输出流。
        Writer fw = new OutputStreamWriter(os,"GBK"); // .  把字节输出流按照指定编码GBK转换成字符输出流。
        fw.write("abc我是中国人");
        fw.close();

    }
}
