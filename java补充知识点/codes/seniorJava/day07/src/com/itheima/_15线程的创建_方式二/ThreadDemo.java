package com.itheima._15线程的创建_方式二;

/**
     目标：线程的创建方式二。

     多线程是很有用的，我们在进程中创建线程的方式有三种:
         （1）直接定义一个类继承线程类Thread，重写run()方法，创建线程对象
          调用线程对象的start()方法启动线程。
         （2）定义一个线程任务类实现Runnable接口，重写run()方法，创建线程任务对象，把
          线程任务对象包装成线程对象， 调用线程对象的start()方法启动线程。
         （3）实现Callable接口（拓展）。

     b.实现Runnable接口的方式。
          -- 1.创建一个线程任务类实现Runnable接口。
          -- 2.重写run()方法
          -- 3.创建一个线程任务对象。
          -- 4.把线程任务对象包装成线程对象
          -- 5.调用线程对象的start()方法启动线程。
     Thread的构造器：
          -- public Thread(){}
          -- public Thread(String name){}
          -- public Thread(Runnable target){}
          -- public Thread(Runnable target,String name){}
     实现Runnable接口创建线程的优缺点：
          缺点：代码复杂一点。
          优点：
            -- 线程任务类只是实现了Runnable接口，可以继续继承其他类，而且可以继续实现其他接口（避免了单继承的局限性）
            -- 同一个线程任务对象可以被包装成多个线程对象
            -- 适合多个多个线程去共享同一个资源（后面内容）
            -- 实现解耦操作，线程任务代码可以被多个线程共享，线程任务代码和线程独立。
            -- 线程池可以放入实现Runable或Callable线程任务对象。(后面了解)
               注意：其实Thread类本身也是实现了Runnable接口的。
            -- 不能直接得到线程执行的结果！
 */
public class ThreadDemo {
    public static void main(String[] args) {
        // 3.创建一个线程任务对象(注意：线程任务对象不是线程对象，只是执行线程的任务的)
        Runnable target = new MyRunnable();
        // 4.把线程任务对象包装成线程对象.且可以指定线程名称
        // Thread t = new Thread(target);
        Thread t = new Thread(target,"1号线程");
        // 5.调用线程对象的start()方法启动线程
        t.start();

        Thread t2 = new Thread(target);
        // 调用线程对象的start()方法启动线程
        t2.start();

        for(int i = 0 ; i < 10 ; i++ ){
            System.out.println(Thread.currentThread().getName()+"==>"+i);
        }
    }
}

// 1.创建一个线程任务类实现Runnable接口。
class MyRunnable implements Runnable{
    // 2.重写run()方法
    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i++ ){
            System.out.println(Thread.currentThread().getName()+"==>"+i);
        }
    }
}