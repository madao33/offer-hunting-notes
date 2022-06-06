package com.itheima._25字节流的使用;
import java.io.FileInputStream;
import java.io.InputStream;
/**
    目标：字节输入流的使用-按照字节数组读取。

    IO流的体系：
            字节流                                   字符流
    字节输入流           字节输出流               字符输入流       字符输出流
    InputStream         OutputStream           Reader         Writer     (抽象类)
    FileInputStream     FileOutputStream       FileReader     FileWriter (实现类)

    a.FileInputStream文件字节输入流。
        -- 作用：以内存为基准，把磁盘文件中的数据按照字节的形式读入到内存中的流。
                简单来说，就是按照字节读取文件数据到内存。
        -- 构造器：
           1.public FileInputStream(File path):创建一个字节输入流管道与源文件对象接通。
           2.public FileInputStream(String pathName):创建一个字节输入流管道与文件路径对接。
        -- 方法：
           1.public int read():每次读取一个字节返回！读取完毕会返回-1。
           2.public int read(byte[] buffer):从字节输入流中读取字节到字节数组中去，
                返回读取的字节数量，没有字节可读返回-1。
        小结：
          public int read(byte[] buffer):从字节输入流中读取字节到字节数组中去，
                返回读取的字节数量，没有字节可读返回-1。
           使用字节数组读取内容，效率可以。
           但是使用字节数组读取文本内容输出，也无法避免中文读取输出乱码的问题。
 */
public class FileInputStreamDemo02 {
    public static void main(String[] args) throws Exception {
        // 需求：读取文件中的数据输出。
        // 1.创建一个文件对象
        //File srcFile = new File("Day09Demo/src/dlei02.txt");
        // 2.创建一个字节输入流管道与源文件对象接通。
        //InputStream is = new FileInputStream(srcFile);

        // 3.简化写法：直接创建一个字节输入流管道与源文件路径接通。
        InputStream is = new FileInputStream("Day09Demo/src/dlei02.txt");

//        // 4.定义一个字节数组读取数据（定义一个桶）
//        byte[] buffer = new byte[3];
//        // 从is管道中读取字节装入到字节数组中去，返回读取字节的数量。
//        int len = is.read(buffer);
//        System.out.println("读取了字节数："+len);
//        String rs = new String(buffer);
//        System.out.println(rs); // abc
//
//        int len1 = is.read(buffer);
//        System.out.println("读取了字节数："+len1);
//        String rs1 = new String(buffer);
//        System.out.println(rs1); // xyz
//
//        int len2 = is.read(buffer);
//        System.out.println("读取了字节数："+len2);
//        // 倒出字节数组中的全部字符
//        //String rs2 = new String(buffer);
//        // 读取了多少就倒出多少！
//        String rs2 = new String(buffer, 0 , len2);
//        System.out.println(rs2); // iyz
//
//        int len3 = is.read(buffer);
//        System.out.println("读取了字节数："+len3); // -1 数据没有了返回-1


        // 读法优化，必须使用循环     // abc xyz i
        // a.定义一个字节数组代表桶   // ooo ooo o
        byte[] buffer = new byte[3];
        int len ; // 存储每次读取的字节数。
        while((len = is.read(buffer)) != -1){
            // 读取了多少就倒出多少！
            String rs = new String(buffer , 0 , len);
            System.out.print(rs);
        }

    }
}
