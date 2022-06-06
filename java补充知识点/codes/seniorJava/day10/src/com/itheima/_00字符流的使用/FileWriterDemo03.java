package com.itheima._00字符流的使用;

import java.io.FileWriter;
import java.io.Writer;

/**
     目标：字符输出流的使用。

     IO流的体系：
            字节流                                   字符流
     字节输入流           字节输出流               字符输入流       字符输出流
     InputStream         OutputStream           Reader         Writer     (抽象类)
     FileInputStream     FileOutputStream       FileReader     FileWriter (实现类)

     d.FileWriter文件字符输出流的使用。
        -- 作用：以内存为基准，把内存中的数据按照字符的形式写出到磁盘文件中去。
            简单来说，就是把内存的数据以字符写出到文件中去。
        -- 构造器：
           public FileWriter(File file):创建一个字符输出流管道通向目标文件对象。
           public FileWriter(String filePath):创建一个字符输出流管道通向目标文件路径。
           public FileWriter(File file,boolean append):创建一个追加数据的字符输出流管道通向目标文件对象。
           public FileWriter(String filePath,boolean append):创建一个追加数据的字符输出流管道通向目标文件路径。
        -- 方法：
             a.public void write(int c):写一个字符出去
             b.public void write(String c)写一个字符串出去：
             c.public void write(char[] buffer):写一个字符数组出去
             d.public void write(String c ,int pos ,int len):写字符串的一部分出去
             e.public void write(char[] buffer ,int pos ,int len):写字符数组的一部分出去
     小结：
        字符输出流可以写字符数据出去，总共有5个方法写字符。
        覆盖管道：
             Writer fw = new FileWriter("Day10Demo/src/dlei03.txt"); // 覆盖数据管道
        追加数据管道：
             Writer fw = new FileWriter("Day10Demo/src/dlei03.txt",true); // 追加数据管道
        换行：
             fw.write("\r\n"); // 换行
        读写字符文件数据建议使用字符流。
 */
public class FileWriterDemo03 {
    public static void main(String[] args) throws Exception {
        // 1.创建一个字符输出流管道通向目标文件路径
        //Writer fw = new FileWriter("Day10Demo/src/dlei03.txt"); // 覆盖数据管道
        Writer fw = new FileWriter("Day10Demo/src/dlei03.txt",true); // 追加数据管道

        // 2.写一个字符出去：public void write(int c):写一个字符出去
        fw.write(97);   // 字符a
        fw.write('b');  // 字符b
        fw.write('磊'); // 字符磊，此时没有任何问题。
        fw.write("\r\n"); // 换行

        // 3.写一个字符串出去：public void write(String c)写一个字符串出去：
        fw.write("Java是最优美的语言！");
        fw.write("我们在黑马学习它！");
        fw.write("\r\n"); // 换行

        // 4.写一个字符数组出去：public void write(char[] buffer):写一个字符数组出去
        fw.write("我爱中国".toCharArray());
        fw.write("\r\n"); // 换行

        // 5.写字符串的一部分出去: public void write(String c ,int pos ,int len):写字符串的一部分出去
        fw.write("Java是最优美的语言！",0,9);
        fw.write("\r\n"); // 换行

        // 6.写字符数组的一部分出去：public void write(char[] buffer ,int pos ,int len):写字符数组的一部分出去
        fw.write("我爱中国".toCharArray(),0 ,2);
        fw.write("\r\n"); // 换行

        fw.close();
    }
}
