package com.itheima._16线程的创建_方式三拓展;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
     拓展：线程的创建方式三。（拓展）

     多线程是很有用的，我们在进程中创建线程的方式有三种:
         （1）直接定义一个类继承线程类Thread，重写run()方法，创建线程对象
              调用线程对象的start()方法启动线程。
         （2）定义一个线程任务类实现Runnable接口，重写run()方法，创建线程任务对象，把
              线程任务对象包装成线程对象， 调用线程对象的start()方法启动线程。
         （3）实现Callable接口（拓展）。

      c.线程的创建方式三: 实现Callable接口。
          -- 1,定义一个线程任务类实现Callable接口 ， 申明线程执行的结果类型。
          -- 2,重写线程任务类的call方法，这个方法可以直接返回执行的结果。
          -- 3,创建一个Callable的线程任务对象。
          -- 4,把Callable的线程任务对象包装成一个未来任务对象。
          -- 5.把未来任务对象包装成线程对象。
          -- 6.调用线程的start()方法启动线程
      优缺点：
          优点：全是优点。
             -- 线程任务类只是实现了Callable接口，可以继续继承其他类，而且可以继续实现其他接口（避免了单继承的局限性）
             -- 同一个线程任务对象可以被包装成多个线程对象
             -- 适合多个多个线程去共享同一个资源（后面内容）
             -- 实现解耦操作，线程任务代码可以被多个线程共享，线程任务代码和线程独立。
             -- 线程池可以放入实现Runable或Callable线程任务对象。(后面了解)
             -- 能直接得到线程执行的结果！
          缺点：编码复杂。
 */
public class ThreadDemo {
    public static void main(String[] args) {
        // 3.创建一个Callable的线程任务对象
        Callable call = new MyCallable();
        // 4.把Callable任务对象包装成一个未来任务对象
        //      -- public FutureTask(Callable<V> callable)
        // 未来任务对象是啥，有啥用？
        //      -- 未来任务对象其实就是一个Runnable对象:这样就可以被包装成线程对象！
        //      -- 未来任务对象可以在线程执行完毕之后去得到线程执行的结果。
        FutureTask<String> task = new FutureTask<>(call);
        // 5.把未来任务对象包装成线程对象
        Thread t = new Thread(task);
        // 6.启动线程对象
        t.start();

        for(int i = 1 ; i <= 10 ; i++ ){
            System.out.println(Thread.currentThread().getName()+" => " + i);
        }

        // 在最后去获取线程执行的结果,如果线程没有结果，让出CPU等线程执行完再来取结果
        try {
            String rs = task.get(); // 获取call方法返回的结果（正常/异常结果）
            System.out.println(rs);
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}

// 1.创建一个线程任务类实现Callable接口，申明线程返回的结果类型
class MyCallable implements Callable<String>{
    // 2.重写线程任务类的call方法！
    @Override
    public String call() throws Exception {
        // 需求：计算1-10的和返回
        int sum = 0 ;
        for(int i = 1 ; i <= 10 ; i++ ){
            System.out.println(Thread.currentThread().getName()+" => " + i);
            sum+=i;
        }
        return Thread.currentThread().getName()+"执行的结果是："+sum;
    }
}