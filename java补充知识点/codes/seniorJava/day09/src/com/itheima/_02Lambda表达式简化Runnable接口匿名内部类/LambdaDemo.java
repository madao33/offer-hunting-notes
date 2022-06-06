package com.itheima._02Lambda表达式简化Runnable接口匿名内部类;

/**
     目标：Lambda表达式简化Runnable接口的匿名内部类写法

     @FunctionalInterface函数式接口注解：
        一旦某个接口加上了这个注解，这个接口只能有且仅有一个抽象方法。
        这个接口就可以被Lambda表达式简化。
 */
public class LambdaDemo {
    public static void main(String[] args) {
         Thread t = new Thread(new Runnable() {
             @Override
             public void run() {
                System.out.println(Thread.currentThread().getName()+":执行~~~");
             }
         });
         t.start();

        Thread t1 = new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+":执行~~~");
        });
        t1.start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+":执行~~~");
        }).start();

        new Thread(() -> System.out.println(Thread.currentThread().getName()+":执行~~~")).start();
    }
}

