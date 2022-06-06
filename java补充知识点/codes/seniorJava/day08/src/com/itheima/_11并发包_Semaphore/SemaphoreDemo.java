package com.itheima._11并发包_Semaphore;

import java.util.concurrent.Semaphore;

/**
    目标：Semaphore的使用介绍。

    引入：
         Semaphore（发信号）的主要作用是控制线程的并发数量。
         synchronized可以起到"锁"的作用，但某个时间段内，只能有一个线程允许执行。
         Semaphore可以设置同时允许几个线程执行。
         Semaphore字面意思是信号量的意思，它的作用是控制访问特定资源的线程数目。
    Semaphore的构造器：
         public Semaphore(int permits)：	permits 表示许可线程的数量
         public Semaphore(int permits, boolean fair)：fair 表示公平性，如果这个设为 true 的话，下次执行的线程会是等待最久的线程
    Semaphore的方法：
         public void acquire() throws InterruptedException	表示获取许可
         public void release()	release() 表示释放许可

    小结：
         Semaphore可以控制并发线程同时进行的数量。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Service service = new Service();
        for(int i = 1 ; i <= 5 ; i++ ){
            new MyThread(service,"线程："+i).start();
        }
    }
}
// 执行的任务。
class Service{
    // 可以同时支持多个线程进入共享资源区执行。
    private Semaphore semaphore = new Semaphore(2);
    public void showMethod(){
        try {
            semaphore.acquire();
            long startTimer = System.currentTimeMillis();
            System.out.println("进入时间："+startTimer);
            System.out.println(Thread.currentThread().getName()+"进入资源执行");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTimer = System.currentTimeMillis();
        System.out.println("结束时间："+endTimer);
        semaphore.release();
        //acquire()和release()方法之间的代码为"同步代码"
    }
}

// 线程类。
class MyThread extends Thread{
    private Service service;
    public MyThread(Service service , String name){
        super(name);
        this.service = service;
    }
    @Override
    public void run() {
        service.showMethod();
    }
}