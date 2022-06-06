package com.itheima._00字符流的使用;

import java.io.FileReader;
import java.io.Reader;

/**
     目标：字符输入流的使用-按照字符数组读取。

     IO流的体系：
            字节流                                       字符流
     字节输入流           字节输出流               字符输入流       字符输出流
     InputStream         OutputStream           Reader         Writer     (抽象类)
     FileInputStream     FileOutputStream       FileReader     FileWriter (实现类)

     c.FileReader:文件字符输入流。
         -- 作用：以内存为基准，把磁盘文件的数据以字符的形式读入到内存。
            简单来说，读取文本文件内容到内存中去。
         -- 构造器：
            public FileReader(File file):创建一个字符输入流与源文件对象接通。
            public FileReader(String filePath):创建一个字符输入流与源文件路径接通。
         -- 方法：
            public int read(): 读取一个字符的编号返回！ 读取完毕返回-1
            public int read(char[] buffer):读取一个字符数组，
                    读取多少个字符就返回多少个数量，读取完毕返回-1
     小结：
         字符流按照字符数组循环读取数据，可以解决中文读取输出乱码的问题，而且性能也较好！！

 */
public class FileReaderDemo02 {
    public static void main(String[] args) throws Exception {
        // 1.创建一个字符输入流管道与源文件接通
        Reader fr = new FileReader("Day10Demo/src/dlei02.txt");
        // 2.按照字符数组读取内容
//        char[] buffer = new char[3];
//        int len = fr.read(buffer);
//        System.out.println("字符数："+len);
//        String rs = new String(buffer);
//        System.out.println(rs);
//
//        int len1 = fr.read(buffer);
//        System.out.println("字符数："+len1);
//        String rs1 = new String(buffer);
//        System.out.println(rs1);
//
//        int len2 = fr.read(buffer);
//        System.out.println("字符数："+len2);
//        String rs2 = new String(buffer,0,len2);
//        System.out.println(rs2);

          // a.按照字符数组读取数据使用循环
          char[] buffer = new char[1024]; // 1K
          // b.定义一个整数记录每次桶读取的字符数据量。
          int len;
          while((len = fr.read(buffer)) != -1 ) {
              // 读取多少倒出多少字符
              System.out.print(new String(buffer, 0 , len));
          }
    }

}
