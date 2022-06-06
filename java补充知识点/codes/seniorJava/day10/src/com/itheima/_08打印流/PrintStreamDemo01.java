package com.itheima._08打印流;

import java.io.*;

/**
    目标：打印流PrintStream / PrintWriter.

    打印流的作用：
        1.可以方便，快速的写数据出去。
        2.可以实现打印啥出去，就是啥出去。
    打印流的构造器：
        public PrintStream(OutputStream os):
        public PrintStream(String filepath):

    小结：
         打印流可以方便，且高效的打印各种数据。
         PrintStream不光可以打印数据，还可以写"字节数据"出去。
         PrintWriter不光可以打印数据，还可以写"字符数据"出去。
 */
public class PrintStreamDemo01 {
    public static void main(String[] args) throws Exception {
        // 1.打印流PrintStream
        //OutputStream os = new FileOutputStream("Day10Demo/src/dlei08.txt");
        //PrintStream ps = new PrintStream(os);
        PrintStream ps = new  PrintStream("Day10Demo/src/dlei08.txt");
        //PrintWriter pw = new  PrintWriter("Day10Demo/src/dlei08.txt");

        ps.println(97); // 写97
        ps.println(110); // 写110
        ps.println("我在黑马快乐的调皮~~");
        ps.println(99.8);
        ps.println(false);
        ps.println('徐');

        // 写字节数据出去
        // ps.write("我爱你".getBytes());

        ps.close();



    }
}
