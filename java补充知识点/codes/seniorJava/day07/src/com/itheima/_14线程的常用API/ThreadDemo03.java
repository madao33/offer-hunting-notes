package com.itheima._14线程的常用API;

/**
    目标：通过Thread类的有参数构造器为当前线程对象取名字。
        -- public Thread()
        -- public Thread(String name):创建线程对象并取名字。
 */
public class ThreadDemo03 {
    // 启动这个类，这个类就是进程，它自带一个主线程，
    // 是main方法，main就是一个主线程的执行！！
    public static void main(String[] args) {
        Thread t1 = new MyThread02("1号线程");
        t1.start();

        Thread t2 = new MyThread02("2号线程");
        t2.start();

        Thread.currentThread().setName("主线程");
        for(int i = 0 ; i < 10 ; i++ ) {
            System.out.println(Thread.currentThread().getName()+" => "+i);
        }
    }
}

// 1.定义一个线程类继承Thread。线程类并不是线程对象，用来创建线程对象的。
class MyThread02 extends Thread{

    public MyThread02(String name) {
        //  public Thread(String name):父类的有参数构造器
        super(name); // 调用父类的有参数构造器初始化当前线程对象的名称！
    }

    // 2.重写run()方法
    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++ ) {
            System.out.println(Thread.currentThread().getName()+" => "+i);
        }
    }
}
