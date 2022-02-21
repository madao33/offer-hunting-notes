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

### 14.5.5 `synchronized`关键字

有关锁和条件的关键之处：

* 锁用来保护代码片段，任何时刻只能有一个线程执行被保护的代码
* 锁可以管理试图进入被保护代码段的线程
* 锁可以拥有一个或多个相关的条件对象
* 每个条件对象管理那些已经进入被保护的代码段但还不能运行的线程

`Java`中的每一个对象都有一个内部锁。如果一个方法用`synchronized`关键字声明，那么对象的锁将保护整个方法。也就是说，要调用该方法，线程必须获得内部的对象锁。

也就是说

```java
public synchronized void method()
{
    // method only
}
```

等价于

```java
public void method()
{
    this.intrinsicLock.lock();
    try
    {
        // method only
    }
    finally { this.intrinsicLock.unlock(); }
}
```

可以简单地声明`Bank`类的`transfer`方法为`synchronized`,而不是使用一个显式的锁。

内部对象锁只有一个相关条件。`wait`方法添加一个线程到等待集中，`notifyAll/notify`方法解除等待线程的阻塞状态。换句话说，调用`wait`或`notityAll`等价于

```java
intrinsicCondition.await();
intrinsicCondition.signalAll();
```

> `wait`、`notifyAll`以及`notify`方法是`Object`类的`final`方法。`Condition`方法必须被命名为`await`、`signalAll`和`signal`以便它们不会与那些方法发生冲突

将静态方法声明为`synchronized`也是合法的

内部锁和条件存在一些局限，包括：

* 不能中断一个正在试图获得锁的线程
* 试图获得锁时不能设定超时
* 每个锁仅有单一的条件，可能是不够的

在代码中应该使用哪一种？`Lock`和`Condition`对象还是同步方法

* 最好既不使用`Lock/Condition`也不使用`synchronized`关键字，可以使用`java.util.concurrent`包中的一种机制，可以处理所有的加锁
* 如果`synchronized`关键字适合使用，就尽量使用，可以减少代码数量，也可以减少出错的几率
* 如果特别需要`Lock/Condition`结构提供的独有特性时，才使用`Lock/Condition`

以下是利用`synchronized`重写的`Bank`类  [Bank.java](synch2/Bank.java)

```java
package chap14.synch2;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;

    public Bank(int n, double initialBalance)
    {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public synchronized void transfer(int from, int to, double amount) throws InterruptedException
    {

        while (accounts[from] < amount)
            wait();
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f", getTotalBalance());
        notifyAll();
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

### 14.5.6 同步阻塞

每一个`Java`对象有一个锁。线程可以通过调用同步方法获得锁。还有另一种机制可以获得锁，通过进入一个同步阻塞

```java
synchronized (obj) // this is the syntax for a synchronized block
{
    // criticadl section
}
```

> 使用一个对象的说来实现额外的原子操作，实际上称为客户端锁定（client-side locking）

### 14.5.7 监视器概念

锁和条件是线程同步的强大工具，但是，严格地讲，它们不是面向对象的。

一种不需要程序员考虑如何加锁的情况下，就可以保证多线程的安全性，最成功的解决方案之一是监视器（monitor）。监视器具有以下特性：

* 监视器是只包含私有域的类
* 每个监视器类的对象有一个相关的锁
* 使用该锁对所有的方法进行加锁。换句话说，如果客户端调用`obj.method()`，那 么`obj`对象的锁是在方法调用开始时自动获得，并且当方法返回时自动释放该锁。因为所有的域是私有的，这样的安排可以确保一个线程在对对象操作时，没有其他线程能访问该域。
* 该锁可以有任意多个相关条件

`Java`中的每一个对象有一个内部的锁和内部的条件。如果一个方法用`synchronized`关键字声明，那么它表现得就像是一个监视器方法。通过调用`wait/notifyAll/notify`来访问条件变量

### 14.5.8 `Volatile`域

`volatile`关键字为实例域的同步访问提供了一种免锁机制。如果声明一个域为volatile,那么编译器和虚拟机就知道该域是可能被另一个线程并发更新的

例如一个`boolean`标记`done`，它的值被一个线程设置却被另一个线程查询，可以使用锁

```java
private boolean done;
public synchronized boolean isDone() { return done; }
public synchronized void setDone() { done = true; }
```

或许使用内部锁可能会导致一些麻烦，这种情况下，或许可以将域声明为`volatile`才比较合理

```java
private volatile boolean done;
public boolean isDone() { return done; }
public void setDone() { done = true; }
```

> `volatile`不能提供原子性，例如方法
>
> ```java
> public void flipDone() { done = !done; }
> ```
>
> 不能确保翻转域中的值。不能保证读取、翻转和写入不被中断

### 14.5.9 `final`变量

还有一种情况可以安全访问一个共享域，即这个域声明为`final`时

```java
final Map<String, Double> accounts = new HashMap<>();
```

其他线程会在构造函数完成构造之后才看到这个`accounts`变量

如果不使用`final`，就不能保证其他线程看到的是`accounts`更新后的值，它们可能都只是看到`null`

### 14.5.10 原子性

假设对共享变量除了赋值之外并不完成其他操作，那么可以将这些共享变量声明为`volatile`

例如，`Atomiclnteger`类提供了方法`incrementAndGet`和`decrementAndGet`,它们分别以原子方式将一个整数自增或自减。例如，可以安全地生成一个数值序列

```java
public static AtomicLong nextNumber = new AtomicLone();
long id = nextNumber.incrementAndGet();
```

`incrementAndGet`方法以原子方式将`AtomicLong`自增，并返回自增后的值。也就是说，获得值、增`1`并设置然后生成新值的操作不会中断

### 14.5.11 死锁

有可能会因为每一个线程要等待更多的钱款存人而导致所有线程都被阻塞。这样的状态称为死锁（deadlock)

### 14.5.12 线程局部变量

线程间共享变量是有一定风险的，有时可能要避免共享变量。例如，`SimpleDateFormat`类不是线程安全的

```java
public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
String dateStamp = dateFormat.format(new Date());
```

结果可能很混乱，因为`dateFormat`使用的内部数据结构可能会被并发的访问所破坏

### 14.5.13 锁测试与超时

线程在调用`lock`方法来获得另一个线程所持有的锁的时候，很可能发生阻塞。`tryLock`方法试图申请一个锁，在成功获得锁后返回`true`,否则，立即返回`false`,而且线程可以立即离开去做其他事情

```java
if (myLock.tryLock())
{
    try { ... }
    finally { myLock.unlock(); }
}
else
    // do something else
```

可以调用`tryLock`时，使用超时参数，像这样

```java
if (myLock.tryLock(100, TimeUnit.MILLISECONDS)) ..
```

* 如果一个线程被另一个线程通过调用`signalAll`或`signal`激活，或者超时时限已达到，或者线程被中断，那么`await`方法将返回
* 如果等待的线程被中断，`await`方法将抛出一个`InterruptedException`异常

### 14.5.14 读/写锁

`java.util.concurrent.locks`包 定 义 了 两 个 锁 类，我 们 已 经 讨 论 的`ReentrantLock`类 和`ReentrantReadWriteLock`类。如果很多线程从一个数据结构读取数据而很少线程修改其中数据的话，后者是十分有用的。在这种情况下，允许对读者线程共享访问是合适的。当然，写者线程依然必须是互斥访问的

读/写锁的必要步骤：

1. 构造一个`ReentrantReadWriteLock`对象

   ```java
   private ReentrantReadWriteLoc rwl = new ReentrantReadWriteLock();
   ```

2. 抽取读锁和写锁：

   ```java
   private Lock readLock = rwl.readLock();
   private Lock writeLock = rwl.writeLock();
   ```

3. 对所有的获取方法加读锁

   ```java
   public double getTotalBalance()
   {
       readLock.lock();
       try { ... }
       finally { readLock.unlock(); }
   }
   ```

4. 对所有的修改方法加写锁：

   ```java
   public void transfer(...)
   {
       writeLock.lock();
       try { ... }
       finally { writeLock.unlock(); }
   }
   ```

### 14.5.15 为什么弃用`stop`和`suspend`方法

`stop`方法用来终止一个线程，以及一个`suspend`方法用来阻塞一个线程直至另一个线程调用`resume`

`stop`、`suspend`和`resume`方法已经弃用。`stop`方法天生就不安全，经验证明`suspend`方法会经常导致死锁

>  线程要终止另一个线程时，无法知道什么时候调用`stop`方法是安全的，什么时候导致对象被破坏。因此，该方法被弃用了。在希望停止线程的时候应该中断线程，被中断的线程会在安全的时候停止。
>
> 如果用`suspend`挂起一个持有一个锁的线程，那么，该锁在恢复之前是不可用的。如果调用`suspend`方法的线程试图获得同一个锁，那么程序死锁：被挂起的线程等着被恢复，而将其挂起的线程等待获得锁

## 14.6 阻塞队列

对于许多线程问题，可以通过使用一个或多个队列以优雅且安全的方式将其形式化。生产者线程向队列插人元素，消费者线程则取出它们。使用队列，可以安全地从一个线程向另一个线程传递数据。

当试图向队列添加元素而队列已满，或是想从队列移出元素而队列为空的时候，阻塞队列（blocking queue)导致线程阻塞

[BlockingQueueTest.java](blockingQueue\BlockingQueueTest.java)

```java
package chap14.blockingQueue;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    private static final  int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final File DUMMY = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args)
    {
        try (Scanner in = new Scanner(System.in))
        {
            System.out.print("Enter base directory :");
            String directory = in.nextLine();
            System.out.print("Enter keyword: ");
            String keyword = in.nextLine();

            Runnable enumerator = () -> {
                try
                {
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                }
                catch (InterruptedException e)
                {
                }
            };

            new Thread(enumerator).start();
            for (int i = 1; i <= SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    try
                    {
                        boolean done = false;
                        while (!done)
                        {
                            File file = queue.take();
                            if (file == DUMMY)
                            {
                                queue.put(file);
                                done = true;
                            }
                            else search(file, keyword);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InterruptedException e)
                    {
                    }
                };
                new Thread(searcher).start();
            }
        }
    }


    public static void enumerate(File directory) throws InterruptedException
    {
        File[] files = directory.listFiles();
        for (File file : files)
        {
            if (file.isDirectory()) enumerate(file);
            else queue.put(file);
        }
    }

    public static void search(File file, String keyword) throws IOException
    {
        try (Scanner in = new Scanner(file, "UTF-8"))
        {
            int lineNumber = 0;
            while (in.hasNextLine())
            {
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword))
                    System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
            }
        }
    }
}

```

## 14.7 线程安全的集合

如果多线程要并发地修改一个数据结构，例如散列表，那么很容易会破坏这个数据结构。可以通过提供锁来保护共享数据结构，但是选择线程安全的实现作为替代可能更容易。

### 14.7.1 高效的映射、集和队列

`java.util.concurrent`包提供了映射、有序集和队列的高效实现：

* `ConcurrentHashMap`
* ``ConcurrentSkipListMap`
* `ConcurrentSkipListSet`
* ``ConcurrentLinkedQueue`

这些集合使用复杂的算法，通过允许并发地访问数据结构的不同部分来使竞争极小化

### 14.7.2 映射条目的原子更新

传统的做法是使用`replace`操作，它会以原子方式用一个新值替换原值，前提是之前没有其他线程把原值替换为其他值

```java
do
{
    oldValue = map.get(word);
    newValue = oldValue == null ? 1 : oldValue + 1;
} while (!map.replace(word, oldValue, newValue));
```

或者，可以使用一个`ConcurrentHashMap<String，AtomicLong>`，或者在JavaSE8中，还可以使用`ConcurrentHashMap<String，LongAdder>`。

```java
map.putIfAbsent(word, new LongAdder());
map.get(word).increment();
```



































