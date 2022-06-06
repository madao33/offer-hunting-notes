package com.itheima._02线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
    目标：创建一个线程池。

    线程池在Java中的代表类：ExecutorService(接口)。

    Java在Executors类下提供了一个静态方法得到一个线程池的对象：
         1.public static ExecutorService newFixedThreadPool(int nThreads)：
            创建一个线程池返回。

    ExecutorService提交线程任务对象执行的方法：
         1.Future<?> submit(Runnable task):提交一个Runnable的任务对象给线程池执行。
    小结：
         pools.shutdown(); // 等待任务执行完毕以后才会关闭线程池
         pools.shutdownNow(); // 立即关闭线程池的代码，无论任务是否执行完毕！
         线程池中的线程可以被复用，线程用完以后可以继续去执行其他任务。
 */
public class ThreadPoolsDemo02 {
    public static void main(String[] args) {
        // a.创建一个线程池，指定线程的固定数量是3.
        // new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
        ExecutorService pools = Executors.newFixedThreadPool(3);
        // b.创建线程的任务对象。
        Runnable target = new MyRunnable();
        // c.把线程任务放入到线程池中去执行。
        pools.submit(target); // 提交任务，此时会创建一个新线程,自动启动线程执行！
        pools.submit(target); // 提交任务，此时会创建一个新线程,自动启动线程执行！
        pools.submit(target); // 提交任务，此时会创建一个新线程,自动启动线程执行！
        pools.submit(target); // 不会再创建新线程，会复用之前的线程来处理这个任务

        pools.shutdown(); // 等待任务执行完毕以后才会关闭线程池
        //pools.shutdownNow(); // 立即关闭线程池的代码，无论任务是否执行完毕！
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        for(int i  = 0 ; i < 5 ; i++ ){
            System.out.println(Thread.currentThread().getName()+" => "+i);
        }
    }
}