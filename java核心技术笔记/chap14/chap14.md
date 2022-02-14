# 第14章 并发

多线程程序在较低的层次上扩展了多任务的概念：一个程序同时执行多个任务。通常，每一个任务称为一个线程`thread`,它是线程控制的简称。可以同时运行一个以上线程的程序称为多线程程序`multithreaded`。

## 14.1 什么是线程

可以发起多个任务，每个任务在自己的线程中运行，一个单独的线程中执行一个任务的简单过程

1. 将任务代码移到了实现`Runnable`接口的类的`run`方法，这个接口很简单，只有一个方法

   ```java
   public interface Runnable
   {
   	void run();
   }
   ```

   因为`Runnable`是一个函数式接口，可以用`lambda`表达式建立一个实例

   ```java
   Runnable r = () -> { task code };
   ```

2. 由`Runnable` 创建一个`Thread`对象

   ```java
   Thread t = new Thread(r);
   ```

3. 启动线程

   ```java
   t.start();
   ```

## 14.2 中断线程

* 当线程的`run`方法执行方法体中最后一条语句后，并经由执行`return`语句返冋时，或者出现了在方法中没有捕获的异常时，线程将终止。
* 没有可以强制线程终止的方法。然而，`interrupt`方法可以用来请求终止线程。对一个线程调用`interrupt`方法时，线程的中断状态将被置位。
* 想要弄清楚中断状态是否被置位，需要调用静态的`Thread.currentThread`方法获得当前线程，然后调用`isInterrupted`方法

```java
while(!Thread.CurrentThread().isInterrupted() && more work to do)
{
    // do more work;
}
```

但是，如果线程被阻塞，就无法检测中断状态。这是产生`InterruptedException`异常的地方。当在一个被阻塞的线程（调用`sleep`或`wait`)上调用`interrupt`方法时，阻塞调用将会被`InterruptedException`异常中断。

如下是线程简单地将中断作为一个终止的请求，这种线程的`run`方法具有如下形式

```java
Runnable r = () -> {
    try
    {
        //...
        while(!Thread.currentThread().isInterrupted() && more work to do)
        {
            // do more work
        }
    }
    catch(InterruptedException e)
    {
        // thread was interrupted during sleep or wait
    }
    finally
    {
        // cleanup, if required
    }
};
```

> 如果在每次工作迭代之后都调用`sleep`方法（或者其他的可中断方法)，`isInterrupted`检测既没有必要也没有用处。如果在中断状态被置位时调用sleep方法，它不会休眠。相反，它将清除这一状态并拋出`InterruptedException`。因此，如果你的循环调用`sleep`，不会检测中断状态。相反，要如下捕获`InterruptedException`异常
>
> ```java
> Runnable r = () -> {
>     try
>     {
>         // ...
>         while(more work to do)
>         {
>             // do more work
>             Thread.sleep(delay);
>         }
>     }
>     catch(InterruptedException e)
>     {
>         // thread was interrupted during sleep
>     }
>     finally
>     {
>         // cleanup, if required
>     }
> };
> ```

有两个非常相似的方法：`interrupted`和`isInterrupted`，这两个方法的区别是

* `Interrupted`方法是一个静态方法，他检测当前的线程是否被中断，而且，调用`interrupted`方法会清除该线程的中断状态
* `isInterrupted`方法是一个实例方法，可用来检验是否有线程被中断，调用这个方法不会改变中断状态

对于`InterruptedException`异常的处理可以有以下两种比较好的方法：

* 在`catch`子句中调用`Thread.currentThread().interrupt()`来设置中断状态

  这种方式设置中断状态之后，调用者可以对其进行检测

  ```java
  void mySubTask()
  {
  	// ...
      try 
      { 
          sleep(delay);
      }
      catch (InterruptedException e)
      {
          Thread.currentThread().interrupt();
      }
  }
  ```

* 更好的选择是，用`throws InterruptedException`标记方法，不采用`try`语句块捕获异常。于是，调用者（或者，最终的`run`方法）可以捕获这一异常

  ```java
  void mySubTask() throws InterruptedException
  {
      // ...
      sleep(delay);
  }
  ```

## 14.3 线程状态

线程可以有如下6种状态：

* `New`(新创建)
* `Runnable`(可运行）
* `Blocked`(被阻塞）
* `Waiting`(等待）
* `Timedwaiting`(计时等待）
* `Terminated`(被终止）

要确定一个线程的当前状态，可以调用`getState`方法

