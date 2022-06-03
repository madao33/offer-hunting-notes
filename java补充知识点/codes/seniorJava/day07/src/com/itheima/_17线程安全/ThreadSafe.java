package com.itheima._17线程安全;

/**
    目标：线程安全问题。

    线程安全问题：多个线程同时操作同一个共享资源的时候可能会出现线程安全问题。

    模拟出取款问题的案例：
        注意：用高度面向对象的思想设计。
        分析：
            （1）提供一个账户类Account.java作为创建共享资源账户对象的类。
            （2）定义一个线程类来用于创建2个线程分别代表小明和小红来取钱。
    小结：
        多个线程同时操作同一个共享资源的时候可能会出现线程安全问题。
 */
public class ThreadSafe {
    public static void main(String[] args) {
        // a.创建一个共享资源账户对象！
        Account acc = new Account("ICBC-110" , 100000);

        // b.创建2个线程对象去账户对象中取钱
        Thread littleMing = new DrawThread(acc,"小明");
        littleMing.start();

        Thread litterRed = new DrawThread(acc,"小红");
        litterRed.start();
    }
}
