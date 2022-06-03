package com.itheima._19线程同步_同步方法;

/**
    目标：线程同步_同步代码块

    线程同步的作用：就是为了解决线程安全问题的方案。

    线程同步解决线程安全问题的核心思想：
            让多个线程实现先后依次访问共享资源，这样就解决了安全问题。
    线程同步的做法：
            是把共享资源进行上锁，每次只能一个线程进入访问完毕以后，其他线程才能进来。

    线程同步的方式有三种：
        （1）同步代码块。
        （2）同步方法。
        （3）lock显示锁。

    b.同步方法
        作用：把出现线程安全问题的核心方法给锁起来，
             每次只能一个线程进入访问，其他线程必须在方法外面等待。
        用法：直接给方法加上一个修饰符 synchronized.
        原理:  同步方法的原理和同步代码块的底层原理其实是完全一样的，只是
              同步方法是把整个方法的代码都锁起来的。
              同步方法其实底层也是有锁对象的：
                  如果方法是实例方法：同步方法默认用this作为的锁对象。
                  如果方法是静态方法：同步方法默认用类名.class作为的锁对象。

 */
public class ThreadSafe {
    public static void main(String[] args) {
        // a.创建一个账户对象。
        Account acc = new Account(10000,"ICBC-110" );
        // b.定义一个线程类创建2个线程代表小明和小红
        Thread xiaoMing = new DrawThread(acc , "小明");
        xiaoMing.start();

        Thread xiaoRed = new DrawThread(acc, "小红");
        xiaoRed.start();
    }
}
