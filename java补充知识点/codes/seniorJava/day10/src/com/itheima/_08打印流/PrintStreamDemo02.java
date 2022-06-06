package com.itheima._08打印流;

import java.io.PrintStream;

/**
    目标：打印流改变输出的流向。重定向。

    System:
        public static void setOut(PrintStream out) :让系统的输出流向打印流。
 */
public class PrintStreamDemo02 {
    public static void main(String[] args) throws Exception {
        System.out.println("==itheima0==");
        PrintStream ps = new PrintStream("Day10Demo/src/log.txt");
        System.setOut(ps); // 让系统的输出流向打印流。

        System.out.println("==itheima1==");
        System.out.println("==itheima2==");
        System.out.println("==itheima3==");
        System.out.println("==itheima4==");
        System.out.println("==itheima5==");
    }
}
