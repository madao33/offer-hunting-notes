package com.itheima._14线程的常用API;

/**
    目标：线程的常用API.

    Thread类的API:
        1.public void setName(String name):给当前线程取名字。
        2.public void getName():获取当前线程的名字。
            -- 线程存在默认名称，子线程的默认名称是：Thread-索引。
            -- 主线程的默认名称就是：main
        3.public static Thread currentThread()
            -- 获取当前线程对象，这个代码在哪个线程中，就得到哪个线程对象。
 */
public class ThreadDemo {
    // 启动后的ThreadDemo当成一个进程。
    // main方法是由主线程执行的，理解成main方法就是一个主线程
    public static void main(String[] args) {
        // 创建一个线程对象
        Thread t1 = new MyThread();
        t1.setName("1号线程");
        t1.start();
        //System.out.println(t1.getName()); // 获取线程名称

        Thread t2 = new MyThread();
        t2.setName("2号线程");
        t2.start();
        //System.out.println(t2.getName());  // 获取线程名称

        // 主线程的名称如何获取呢？
        // 这个代码在哪个线程中，就得到哪个线程对象。
        Thread m = Thread.currentThread();
        m.setName("最强线程main");
        //System.out.println(m.getName()); // 获取线程名称

        for(int i = 0 ; i < 10 ; i++ ){
            System.out.println(m.getName()+"==>"+i);
        }
    }
}

// 1.定义一个线程类继承Thread类。
class MyThread extends Thread{
    // 2.重写run()方法
    @Override
    public void run() {
        // 线程的执行方法。
        for(int i = 0 ; i < 10 ; i++ ){
            System.out.println(Thread.currentThread().getName()+"==>"+i);
        }
    }
}