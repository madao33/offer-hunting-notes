package com.itheima._12线程的创建_方式一;

/**
    目标：线程的创建方式一。

    多线程是很有用的，我们在进程中创建线程的方式有三种:
        （1）直接定义一个类继承线程类Thread，重写run()方法，创建线程对象
            调用线程对象的start()方法启动线程。
        （2）定义一个线程任务类实现Runnable接口，重写run()方法，创建线程任务对象，把
            线程任务对象包装成线程对象， 调用线程对象的start()方法启动线程。
        （3）实现Callable接口（拓展）。

    a.继承Thread类的方式
        -- 1.定义一个线程类继承Thread类。
        -- 2.重写run()方法
        -- 3.创建一个新的线程对象。
        -- 4.调用线程对象的start()方法启动线程。

        继承Thread类的优缺点：
            优点：编码简单。
            缺点：线程类已经继承了Thread类无法继承其他类了，功能不能通过继承拓展（单继承的局限性）
    小结：
        线程类是继承了Thread的类。
        启动线程必须调用start()方法。
        多线程是并发抢占CPU执行，所以在执行的过程中会出现并发随机性。
 */
public class ThreadDemo {
    // 启动后的ThreadDemo当成一个进程。
    // main方法是由主线程执行的，理解成main方法就是一个主线程
    public static void main(String[] args) {
        // 3.创建一个线程对象
        Thread t = new MyThread();
        // 4.调用线程对象的start()方法启动线程,最终还是执行run()方法！
        t.start();

        for(int i = 0 ; i < 100 ; i++ ){
            System.out.println("main线程输出："+i);
        }
    }
}

// 1.定义一个线程类继承Thread类。
class MyThread extends Thread{
    // 2.重写run()方法
    @Override
    public void run() {
        // 线程的执行方法。
        for(int i = 0 ; i < 100 ; i++ ){
            System.out.println("子线程输出："+i);
        }
    }
}