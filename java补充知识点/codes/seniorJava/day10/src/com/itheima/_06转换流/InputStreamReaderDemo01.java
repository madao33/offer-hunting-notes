package com.itheima._06转换流;

import java.io.*;

/**
     目标：字符输入转换流InputStreamReader的使用。

             字节流                                     字符流
     字节输入流               字节输出流              字符输入流            字符输出流
     InputStream             OutputStream          Reader               Writer   (抽象类)
     FileInputStream         FileOutputStream      FileReader           FileWriter(实现类)
     BufferedInputStream     BufferedOutputStream  BufferedReader       BufferedWriter(实现类，缓冲流)
                                                   InputStreamReader    OutputStreamWriter
     字符输入转换流InputStreamReader:
          -- 作用：可以解决字符流读取不同编码乱码的问题。
                  可以把原始的字节流按照当前默认的代码编码转换成字符输入流。
                  也可以把原始的字节流按照指定编码转换成字符输入流

          -- 构造器：
                public InputStreamReader(InputStream is)：可以使用当前代码默认编码转换成字符流，几乎不用！
                public InputStreamReader(InputStream is,String charset):可以指定编码把字节流转换成字符流
     小结：
         字符输入转换流可以把字节输入流按照默认编码转换成字符输入流。
            -- Reader isr = new InputStreamReader(is); // 使用当前代码默认编码UTF-8转换成字符流，几乎不用！
         字符输入转换流也可以指定编码把字节输入流转换成字符输入流。
            -- Reader isr = new InputStreamReader(is,"GBK"); // 指定编码把字节流转换成字符流
         字符输入转换流可以解决不同编码读取乱码的问题！
 */
public class InputStreamReaderDemo01 {
    public static void main(String[] args) throws Exception {
        // 代码：UTF-8    文件：GBK(ab我爱你： o o [oo] [oo] [oo])
        // 1.提取GBK文件的原始字节流
        InputStream is = new FileInputStream("D:\\itcast\\网络编程公开课\\Netty.txt");
        // 2.把原始字节输入流通过转换流，转换成 字符输入转换流InputStreamReader
        //Reader isr = new InputStreamReader(is); // 使用当前代码默认编码UTF-8转换成字符流，几乎不用！
        Reader isr = new InputStreamReader(is,"GBK"); // 指定编码把字节流转换成字符流
        // 3.包装成缓冲流
        BufferedReader br = new BufferedReader(isr);
        // 4.定义一个字符串变量存储每行数据
        String line;
        // 使用一个循环读取数据(经典代码)
        while((line = br.readLine())!=null){
            System.out.println(line);
        }
    }
}
