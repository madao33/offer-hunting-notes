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

### 14.3.1 新创建线程

当用`new`操作符创建一个新线程时，如`new Thread(r)`，该线程还没有开始运行。这意味着它的状态是`new`。当一个线程处于新创建状态时，程序还没有开始运行线程中的代码。在线程运行之前还有一些基础工作要做。

### 14.3.2 可运行线程

* 一旦调用`start`方法，线程处于`runnable`状态
* 一旦一个线程开始运行，它不必始终保持运行
* 现在所有的桌面以及服务器操作系统都使用抢占式调度
* 在具有多个处理器的机器上，每一个处理器运行一个线程，可以有多个线程并行运行。当然，如果线程的数目多于处理器的数目，调度器依然采用时间片机制

### 14.3.3 被阻塞线程和等待线程

当线程处于被阻塞或等待状态时，它暂时不活动

* 当一个线程试图获取一个内部的对象锁（而不是`java.util.concurrent`库中的锁)，而该锁被其他线程持有，则该线程进入阻塞状态。当所有其他线程释放该锁，并且线程调度器允许本线程持有它的时候，该线程将变成非阻塞状态
* 当线程等待另一个线程通知调度器一个条件时，他自己进入等待状态
* 有几个方法有一个超时参数。调用他们导致线程进入计时等待（timed waiting）状态

### 14.3.4 被终止的线程

线程因如下两个原因之一而被终止：

* 因为run方法正常退出而自然死亡
* 因为一个没有捕获的异常终止了`run`方法而意外死亡

## 14.4 线程属性

### 14.4.1 线程优先级

在Java程序设计语言中，每一个线程有一个优先级。默认情况下，一个线程继承它的父线程的优先级。可以用`setPriority`方法提高或降低任何一个线程的优先级。可以将优先级设置为在`MIN_PRIORITY`(在`Thread`类中定义为1)与`MAX_PRIORITY`(定义为10)之间的任何值。`NORM_PRIORITY`被定义为5。

* 每当线程调度器有机会选择新线程时，它首先选择具有较高优先级的线程
* 但是线程的优先级高度依赖于系统的，不要将程序构建为功能正确性依赖于优先级

### 14.4.2 守护线程

可以调用

```java
t.setDaemon(true);
```

将线程转换为守护线程（daemon thread）

守护线程的唯一用途是为其他线程提供服务

### 14.4.3 未捕获异常处理器

线程的`run`方法不能抛出任何受查异常，但是，非受査异常会导致线程终止

不需要任何`catch`子句来处理可以被传播的异常。相反，就在线程死亡之前，异常被传递到一个用于未捕获异常的处理器

## 14.5 同步

在大多数实际的多线程应用中，两个或两个以上的线程需要共享对同一数据的存取，如果两个线程都调用了一个修改该对象状态的方法，就会产生错误，这种情况就是竞争条件（race condition）

### 14.5.1 竞争条件的一个例子

为了避免多线程引起的对共享数据的说误，必须学习如何同步存取

以下一个测试程序模拟账户之间资金的转移，每一个交易都用一个线程进行随机的转移

[UnsynchBankTest.java](unsynch/UnsynchBankTest.java)是`Runnable`类的代码

```java
package chap14.unsynch;

public class UnsynchBankTest {
    public static final int MACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args)
    {
        Bank bank = new Bank(MACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < MACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try
                {
                    while (true)
                    {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                }
                catch (InterruptedException e)
                {

                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}

```

[Bank.java](unsynch/Bank.java)是`Bank`类的代码

```java
package chap14.unsynch;

import java.util.Arrays;

public class Bank {
    private final double[] accounts;

    public Bank(int n, double initialBalance)
    {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount)
    {
        if (accounts[from] < amount) return;
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f", getTotalBalance());
    }

    public double getTotalBalance()
    {
        double sum = 0;

        for (double a : accounts)
            sum += a;

        return sum;
    }

    public int size()
    {
        return accounts.length;
    }
}

```

运行的结果如下所示

```shell
Thread[Thread-4,5,main]
Thread[Thread-5,5,main]
Thread[Thread-3,5,main]
Thread[Thread-1,5,main]
     493.67 from 1 to 51Thread[Thread-0,5,main]
     474.96 from 0 to 3 Total Balance:   99250.65Thread[Thread-99,5,main]
     496.53 from 99 to 29Thread[Thread-98,5,main]
     283.62 from 98 to 74Thread[Thread-97,5,main]
Thread[Thread-96,5,main]
     492.44 from 96 to 78 Total Balance:   99127.50Thread[Thread-95,5,main]

```

虽然账户之间的资金转移比较频繁，但是需要知道一点，所有的操作都是账户之间的资金转移，没有和外部的资金交流，所以所有的账户总额应该是不变的，但是程序运行的结果不是这样的

### 14.5.2 竞争条件详解

上面的银行账户资金转移程序结果的错误可以考虑这一种情况，当两个线程试图同时更新同一个账户的时候，这个问题就出现了，假设两个线程同时执行指令

```java
accounts[to] += amount;
```

真正的问题是`transfer`方法的执行过程中可能会被中断。如果能够确保线程在失去控制之前方法运行完成，那么银行账户对象的状态永远不会出现讹误。

### 14.5.3 锁对象

利用`ReentrantLock`保护代码块的基本结构如下

```java
mylock.lock();
try
{
    // critical section
}
finally
{
    myLock.unlock();
}
```

这一结构确保任何时刻只有一个线程进人临界区。一旦一个线程封锁了锁对象，其他任何线程都无法通过`lock`语句。当其他线程调用`lock`时，它们被阻塞，直到第一个线程释放锁对象。

> 把解锁操作括在`finally`子句之内是至关重要的。如果在临界区的代码抛出异常，锁必须被释放。否则，其他线程将永远阻塞

修改[Bank.java](unsynch/Bank.java)类，添加一个锁

```java
package chap14.unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock bankLock = new ReentrantLock();
    public Bank(int n, double initialBalance)
    {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount)
    {
        bankLock.lock();
        try
        {
            if (accounts[from] < amount) return;
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f", getTotalBalance());
        }
        finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance()
    {
        double sum = 0;

        for (double a : accounts)
            sum += a;

        return sum;
    }

    public int size()
    {
        return accounts.length;
    }
}

```

假定一个线程调用`transfer`,在执行结束前被剥夺了运行权。假定第二个线程也调用`transfer`,由于第二个线程不能获得锁，将在调用`lock`方法时被阻塞。它必须等待第一个线程完成`transfer`方法的执行之后才能再度被激活。当第一个线程释放锁时，那么第二个线程才能开始运行。这样程序运行的结果就正确了，账户的总额保持不变，运行的结果如下

```shell
754.67 from 44 to 28 Total Balance:  100000.00Thread[Thread-95,5,main]
     156.31 from 95 to 85 Total Balance:  100000.00Thread[Thread-49,5,main]
     735.95 from 49 to 47 Total Balance:  100000.00Thread[Thread-9,5,main]
      85.83 from 9 to 65 Total Balance:  100000.00Thread[Thread-73,5,main]
     782.29 from 73 to 39 Total Balance:  100000.00Thread[Thread-62,5,main]
     163.60 from 62 to 18 Total Balance:  100000.00Thread[Thread-92,5,main]
      17.42 from 92 to 63 Total Balance:  100000.00Thread[Thread-30,5,main]
     133.68 from 30 to 59 Total Balance:  100000.00Thread[Thread-57,5,main]
      40.31 from 57 to 42 Total Balance:  100000.00Thread[Thread-42,5,main]
     574.76 from 42 to 69 Total Balance:  100000.00Thread[Thread-86,5,main]
     …………
```

> 锁是可重入的，因为线程可以重复地获得已经持有的锁。锁保持一个持有计数（`holdcount`)来跟踪对`lock`方法的嵌套调用。线程在每一次调用`lock`都要调用`unlock`来释放锁。由于这一特性，被一个锁保护的代码可以调用另一个使用相同的锁的方法。
>
> 要留心临界区中的代码，不要因为异常的抛出而跳出临界区。如果在临界区代码结束之前抛出了异常，finally子句将释放锁，但会使对象可能处于一种受损状态。

### 14.5.4 条件对象

通常，线程进人临界区，却发现在某一条件满足之后它才能执行。要使用一个**条件对象**来管理那些已经获得了一个锁但是却不能做有用工作的线程，条件对象也叫条件变量（conditional variable）

一个锁对象可以有一个或多个相关的条件对象。你可以用`newCondition`方法获得一个条件对象

```java
class Bank
{
    private Condition sufficientFunds;
    
    public Bank()
    {
        sufficientFunds = bankLock.newCondition();
    }
}
```

如果`transfer`方法发现余额不足，它调用`sufficientFunds.await();`， 当前线程现在被阻塞了，并放弃了锁

等待获得锁的线程和调用`await`方法的线程存在本质上的不同。一旦一个线程调用`await`方法，它进人该条件的等待集。当锁可用时，该线程不能马上解除阻塞。相反，它处于阻塞状态，直到另一个线程调用同一条件上的`signalAll`方法时为止

当另一个线程转账时，它应该调用

```java
sufficientFunds.signalAll();
```

这一调用重新激活因为这一条件而等待的所有线程，一旦锁成为可用的，它们中的某个将从`await`调用返回，获得该锁并从被阻塞的地方继续执行

> `signalAll`方法仅仅是通知正在等待的线程：此时有可能已经满足条件，值得再次去检测该条件

对于上面的[Bank.java](unsynch/Bank.java)，应该在账户余额发生变化后，也就是完成转账时，调用`signalAll`方法

```java
public void transfer(int from, int to, int amount)
{
    bankLock.lock();
    try
    {
        while (accounts[from] < amount)
            sufficientFunds.await();
        // transfer funds
        
        sufficientFunds.signalAll();
    }
    finally
    {
        bankLock.unlock();
    }
}
```

> 注意调用`signalAll`不会立即激活一个等待线程。它仅仅解除等待线程的阻塞，以便这些线程可以在当前线程退出同步方法之后，通过竞争实现对对象的访问

添加同步之后的代码，总金额永远是$\$100,000$，没有任何账户曾经出现负的余额，但是因为同步机制的簿记操作，程序的运行稍微有些慢

[Bank.java](synch/Bank.java)

```java
package chap14.synch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock bankLock = new ReentrantLock();
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance)
    {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException
    {
        bankLock.lock();
        try
        {
            while (accounts[from] < amount)
                sufficientFunds.await();
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f", getTotalBalance());
            sufficientFunds.signalAll();
        }
        finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance()
    {
        double sum = 0;

        for (double a : accounts)
            sum += a;

        return sum;
    }

    public int size()
    {
        return accounts.length;
    }
}

```

