package com.itheima._06原子性问题的解决;

/**
    目标：问题解决。

    如何保证变量访问的原子性呢?
        1.加锁实现线程安全。
        2.基于CAS方式的原子类。
 */
public class VolatileAtomicThread implements Runnable {
    // 定义一个int类型的遍历
    private volatile int count = 0 ;
    @Override
    public void run() {
        // 对该变量进行++操作，100次
        for(int x = 0 ; x < 100 ; x++) {
           synchronized (this){
               count++ ;
               System.out.println(Thread.currentThread().getName() + "count =========>>>> " + count);
           }
        }
    }
}

class VolatileAtomicThreadDemo {
    public static void main(String[] args) {
        // 创建VolatileAtomicThread对象
        Runnable target = new VolatileAtomicThread() ;
        // 开启100个线程对执行这一个任务。
        for(int x = 0 ; x < 100 ; x++) {
            new Thread(target).start();
        }
    }

}