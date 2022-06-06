package com.itheima._04volatile关键字深入学习;

/**
    目标：volatile关键字深入学习。

    引入:
        问题：线程修改了某个成员变量的值，但是在主线程中读取到的还是之前的值
             修改后的值无法读取到。
    原因：按照JMM模型，所有的成员变量和静态变量都存在于主内存中，主内存中的变量可以被多个线程共享。
        每个线程都存在一个专属于自己的工作内存，工作内存一开始存储的是成员变量的副本。
        所以线程很多时候都是直接访问自己工作内存中的该变量，其他线程对主内存变量值的修改将不可见！！

    解决此问题：
        希望所有线程对于主内存的成员变量修改，其他线程是可见的。
        （1）加锁：可以实现其他线程对变量修改的可见性。
             某一个线程进入synchronized代码块前后，执行过程入如下：
             a.线程获得锁
             b.清空工作内存
             c.从主内存拷贝共享变量最新的值到工作内存成为副本

        （2）可以给成员变量加上一个volatile关键字，立即就实现了成员变量多线程修改的可见性。

    小结：
         可以给成员变量加上一个volatile关键字，当一个线程修改了这个成员变量的值，其他线程可以立即看到修改后的值并使用！
         volatile与synchronized的区别。
         - volatile只能修饰实例变量和静态变量，而synchronized可以修饰方法，以及代码块。
         - volatile保证数据的可见性，但是不保证原子性(多线程进行写操作，不保证线程安全);
          而synchronized是一种排他（互斥）的机制，
 */
public class VolatileDemo01 {
    public  static void main(String[] args) {
        // 1.启动线程，把线程对象中的flag改为true。
        VolatileThread t = new VolatileThread();
        t.start();

        // 2.定义一个死循环
        while(true){
            // 这里读取到了flag值一直是false,虽然线程已经把它的值改成了true。
            if(t.isFlag()){
                System.out.println("执行了循环一次~~~~~~~");
            }
        }
       /* while(true){
            synchronized ("ddd"){
                // 这里读取到了flag值一直是false,虽然线程已经把它的值改成了true。
                if(t.isFlag()){
                    System.out.println("执行了循环一次~~~~~~~");
                }
            }
        }*/
    }
}
// 线程类。
class VolatileThread extends Thread {
    // 定义成员变量
    // volatile可以实现变量一旦被子线程修改，其他线程可以马上看到它修改后的最新值！
    private  boolean flag = false ;
    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 将flag的值更改为true
        this.flag = true ;
        System.out.println("线程修改了flag=" + flag);
    }
}