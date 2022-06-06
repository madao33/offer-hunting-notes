package com.itheima._03死锁;

/**
     目标：死锁。

     死锁是这样一种情形：多个线程同时被阻塞，它们中的一个或者全部都在等待某个资源被释放。
            由于线程被无限期地阻塞，因此程序不可能正常终止。

            客户（占用资金，等待经销商的货品资源）   经销商（占用货品资源，等待客户的资金）

     java 死锁产生的四个必要条件：
         1、互斥使用，即当资源被一个线程使用(占有)时，别的线程不能使用。
         2、不可抢占，资源请求者不能强制从资源占有者手中夺取资源，资源只能由资源占有者主动释放。
         3、请求和保持，即当资源请求者在请求其他的资源的同时保持对原有资源的占有。
         4、循环等待，即存在一个等待循环队列：p1要p2的资源，p2要p1的资源。这样就形成了一个等待环路

         当上述四个条件都成立的时候，便形成死锁。当然，死锁的情况下如果打破上述任何一个条件，
         便可让死锁消失

     代码实现。

     小结：
        死锁是多个线程满足上述四个条件才会形成，死锁需要尽量避免。
        死锁一般存在资源的嵌套请求！
 */
public class ThreadDead {
    // 1.至少需要两个资源，每个资源只需要1份。
    public static Object resources1 = new Object();
    public static Object resources2 = new Object();

    public static void main(String[] args) {
        // 2.创建2个线程。
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 线程1：占用资源1 ，请求资源2
                synchronized (resources1){
                    System.out.println("线程1已经占用了资源1，开始请求资源2");
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (resources2){
                        System.out.println("线程1已经占用了资源2");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resources2){
                    System.out.println("线程2已经占用了资源2，开始请求资源1");
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (resources1){
                        System.out.println("线程2已经占用了资源1");
                    }
                }
            }
        }).start();
    }
}