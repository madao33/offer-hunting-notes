package com.itheima._25字节流的使用;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
    拓展：解决字节输入流读取中文内容输出乱码的问题。

    引入：
        一个一个字节读取中文输出
        一个一个字节数组读取中文输出均无法避免乱码。
    如何实现读取可以避免乱码呢？
        1.定义一个字节数组与文件的大小刚刚一样大，然后一桶水读取全部字节数据再输出！
    小结：
        定义一个字节数组与文件的大小刚刚一样大，然后一桶水读取全部字节数据再输出！
        可以避免中文读取输出乱码，但是如果读取的文件过大，会出现内存溢出！！

        字节流并不适合读取文本文件内容输出，读写文件内容建议使用字符流。
 */
public class FileInputStreamDemo03 {
    public static void main(String[] args) throws Exception {
        // 0.定位文件对象
        File f = new File("Day09Demo/src/dlei03.txt");
        // 1.定义一个字节输入流通向源文件路径，简化写法！
        InputStream is = new FileInputStream(f);

        // 2.定义一个字节数组与文件的大小刚刚一样大
//        System.out.println("文件大小："+f.length());
//        byte[] buffer = new byte[(int) f.length()];
//        int len = is.read(buffer);
//        System.out.println("读取了："+len);
//        String rs = new String(buffer);
//        System.out.println(rs);

        byte[] buffer = is.readAllBytes();
        String rs = new String(buffer);
        System.out.println(rs);

    }
}
