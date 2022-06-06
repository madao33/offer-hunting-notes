package com.itheima._05原子性介绍;

/**
    目标：原子性。

    概述：所谓的原子性是指在一次操作或者多次操作中，
         所有的操作全部都得到了执行并且不会受到任何因素的干扰。最终结果要保证线程安全。

    小结：在多线程环境下，volatile关键字可以保证共享数据的可见性，
         但是并不能保证对数据操作的原子性（在多线程环境下volatile修饰的变量也是线程不安全的）。
         volatile的使用场景
             - 开关控制
             利用可见性特点，控制某一段代码执行或者关闭(比如今天课程的第一个案例)。
             - 多个线程操作共享变量，但是是有一个线程对其进行写操作，其他的线程都是读。此时加上更好，其他线程可以立即读取到最新值。
         volatile不能保证变量操作的原子性（安全性）。
 */
public class VolatileAtomicThread implements Runnable {
    // 定义一个int类型的遍历
    private volatile int count = 0 ;
    @Override
    public void run() {
        // 对该变量进行++操作，100次
        for(int x = 0 ; x < 100 ; x++) {
            count++ ;					
            System.out.println("count =========>>>> " + count);
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