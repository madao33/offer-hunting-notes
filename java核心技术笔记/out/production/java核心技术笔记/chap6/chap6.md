# 第6章 接口、lambda表达式与内部类

* 接口（`interface`）技术：这种技术主要用来描述类具有什么功能，而并不给出每个功能的具体实现。一个类可以实现（`implement`）一个或多个接口，并在需要接口的地方，随时使用实现了相应接口的对象
* `lambda`表达式：这是一种表示可以在将来某个时间点执行的代码块的简洁方法。使用`lambda`表达式，可以用一种精巧而简洁的方式表示使用回调或变量行为的代码
* 内部类（`inner class`）机制：内部类（`innerclass`）机制。理论上讲，内部类有些复杂，内部类定义在另外一个类的内部，其中的方法可以访问包含它们的外部类的域。内部类技术主要用于设计具有相互协作关系的类集合
* 代理（`proxy`）: 这是一种实现任意借口的对象，代理是一种非常专业的构造工具，可以用来构建系统级的工具

## 6.1 接口

### 6.1.1 接口概念

在`Java`程序设计语言中，接口不是类，而是对类的一组需求描述，这些类要遵从接口描述的统一格式进行定义

接口中的所有方法自动地属于`public`。因此，在接口中声明方法时，不必提供关键字`public`

```java
public interface Comparable
{
    int compareTo(Object other);
}
```

* 接口可以定义常量
* 接口绝不能含有实例域
* 接口不能引用实例域

为了让类实现一个接口，通常需要下面两个步骤：

1. 将类声明为实现给定的接口
2. 对接口中的所有方法进行定义

```java
class Employee implements Comparable{
	// ...
    public int comparaTo(Object otherObject)
    {
        Employee other = (Employee) otherObject;
        return Double.compare(salary, other.salary);
    }
}
```

在这里, 我们使用了静态 Double.compare 方法 , 如果第一个参数小于第二个参数 , 它会返回一个负值 ; 如果二者相等则返回 0; 否则返回一个正值 

对泛型`Comparable`接口提供一个类型参数

```java
class Employee implements Comparable<Employee>
{
	public int compareTo(Employee other)
    {
        return Double.compare(salary, other.salary);
    }
}
```

> Java 程序设计语言是一种强类型 ( strongly typed ) 语言 。 在调用方法的时
> 候 , 编译器将会检查这个方法是否存在 

### 6.1.2 接口的特性

* 接口不是类， 尤其不能使用 new 运算符实例化一个接口

  `x = new Comparable(...); //ERROR`

* 然而，尽管不嫩构造接口的对象，却能声明接口的变量

  `Compare x; //OK`

* 接口变量必须应用实现了接口的类对象

  `x = new Employee(...); // OK provided Employee implements Comparable`

* 可以使用`instance`检测一个对象是否实现了某个特定的接口

  `if(anObject instanceof Comparable) {...}`

### 6.1.3 接口与抽象类

`Java`不支持多继承，所以引入了接口，每个类可以实现多个接口

```java
class Employee extends Person implements Comparable, Cloneable{...}
```

### 6.1.6 解决默认方法冲突

* 超类优先 如果超类提供了一个具体方法，同名而且有相同参数类型的默认方法会被忽略
* 接口冲突 如果一个超接口提供了一个默认方法，另一个接口提供了一个同名而且参数类型（不论是否是默认参数）相同的方法，必须覆盖这个方法来解决冲突

## 6.2 接口示例

### 6.2.1 接口与回调

**回调（callback）**是一种常见的程序设计模式，在这种模式中，可以指出某个特定时间发送时应该采取的动作。

利用`java.awt.event`的`ActionListener`接口实现了10秒钟打印一条数据，并响一声

```java
package chap6.timer;

import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

public class TimerTest {
    public static void main(String[] args)
    {
        ActionListener listener = new TimePrinter();

        Timer t = new Timer(10000, listener);
        t.start();
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TimePrinter implements ActionListener
{
    public void actionPerformed(ActionEvent event)
    {
        System.out.println("At the tone, the time is " + new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}
```

### 6.2.3 对象克隆

拷贝即原变量和副本都是同一个对象的引用，任何一个变量改变都会影响另一个变量

```java
Employee original = new Employee("John Public", 50000);
Employee copy = original;
copy.raiseSalary(10);
```

如果是想要新的对象初始状态和原变量相同，但是之后改变的状态不同，这时候就需要使用`clone`方法

```java
Employee copy = original.clone();
copy.raiseSalary(10);
```

> `Object`类实现`clone`是逐个域拷贝，如果对象包含子对象的引用，拷贝域就会得到相同子对象的另一个引用，这样一来，原对象和克隆的对象仍然会共享一些信息

例如`Employee`类的`hireDay`是`Date`，这是可变的，所以它也需要克隆

```java
class Employee implements Cloneable
{
    // ...
    public Employee clone() throws CloneNotSuppportedException
    {
        Employee cloned = (Employee) super.clone();
        cloned.hireDay = (Date) hireDay.clone();
        return cloned;
    }
}
```

## 6.3 `lambda`表达式

### 6.3.2 `lambda`表达式的语法

```java
(String first, String second)
	-> first.length() - second.length()
```

`chap6/interface/lambda/LambdaTest.java`

```java
package chap6.lambda;

import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class LambdaTest {
    public static void main(String[] args)
    {
        String[] planets = new String[] {"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Sature", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order: ");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted by length: ");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(planets));

        Timer t = new Timer(1000, event -> System.out.println("The time is " + new Date()));
        t.start();

        JOptionPane.showMessageDialog(null, "Quit program>");
        System.exit(0);
    }
}
```

## 6.4

内部类（`innerclass`）是定义在另一个类中的类，使用内部类的原因：

* 内部类方法可以访问该类定义所在的作用域中的数据，包括私有的数据
* 内部类可以对同一个包中的其他类隐藏起来
* 当想要定义一个回调函数且不想编写大量代码时，使用匿名（`anonymous`）内部类比较便捷

### 6.4.1 使用内部类访问对象状态

内部类既可以访问自身的数据域，也可以访问创建它的外围类的数据域

`chap6/innerClass/InnerClassTest.java`

```java
package chap6.innerClass;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class InnerClassTest {
    public static void main(String[] args){
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

class TalkingClock
{
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep)
    {
        this.interval = interval;
        this.beep = beep;
    }

    public void start()
    {
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval, listener);
        t.start();
    }

    public class TimePrinter implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("At the tone, the time is " + new Date());
            if (beep) Toolkit.getDefaultToolkit().beep();
        }
    }
}
```

### 6.4.2 内部类的特殊语法规则

内部类引用外围类的正规语法

```java
OuterClass.this
```

可以使用下面的方式编写`TimerPrinter`内部类的`actionPerformed`方法

```java
public void actionPerformed(ActionEvent event)
{
    // ...
    if(TalkingClock.this.beep) Toolkit.getDefaultToolKit().beep();
}
```

反过来，也可以使用下列盘语法编写内部对象的构造器

```java
outerObject.new InnerClass(construction parameters)
```

例如

```java
ActionListener listener = this.new TimePrinter();
```

在外围类的作用域之外，可以这样引用内部类：

```java
OuterClass.InnerClass
```

### 6.4.4 局部内部类

上述的代码`TimerPrinter`这个类名字只在`start`方法中创建这个类型对象时使用了一次，当遇到这类情况时，可以在一个方法中定义局部类

```java
public void start()
{
    class TimePrinter implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("At the tone, the time is " + new Date());
            if(beep) Toolkit.getDefaultToolkit().beep();
        }
    }
    
    ActionListener listener = new TimePrinter();
    Timer t = new Timer(interval, listener);
    t.start();
}
```

局部类不能使用`public`或`private`访问说明符进行声明，它的作用域被限定在声明这个局部类的块中

### 6.4.6 匿名内部类

```java
public vodi start(int interval, boolean beep)
{
    ActionListener listener = new ActionListener()
    {
        public void actionPerformed(ActionEvent event)
        {
            System.out.println("At the tone, the time is " + new Date());
            if(beep) Toolkit.getDefaultToolkit().beep();
        }
    }
    Timer t = new Timer(interval, listener);
    t.start();
}
```

### 6.4.7 静态内部类

有时候，使用内部类只是为了把一个类隐藏在另外一个类的内部，并不需要内部类引用外围类对象。为此，可以将内部类声明为`static`，以便取消产生的引用

例如以下代码

```java
double min = Double.POSITIVE_INFINITY;
double max = Double.NEGATIVE_INFINITY;
for (double v : values)
{
    if(min > v) min = v;
    if(max < v) max = v;
}
```

`staticInnerClass/StaticInnerClassTest.java`

```java
package chap6.staticInnerClass;

public class StaticInnerClassTest {
    public static void main(String[] args)
    {
        double[] d = new double[20];
        for(int i = 0; i < d.length; i++)
            d[i] = 100 * Math.random();
        ArrayAlg.Pair p = ArrayAlg.minmax(d);
        System.out.println("min = " + p.getFirst());
        System.out.println("max = " + p.getSecond());
    }
}

class ArrayAlg
{
    public static class Pair
    {
        private double first;
        private double second;

        public Pair(double f, double s)
        {
            first = f;
            second = s;
        }

        public double getFirst()
        {
            return first;
        }

        public double getSecond(){
            return second;
        }
    }

    public static Pair minmax(double[] values)
    {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for(double v : values)
        {
            if(min > v) min = v;
            if(max < v) max = v;
        }
        return new Pair(min, max);
    }
}
```

## 6.5 代理

使用代理可以在运行时创建一个实现一组给定接口的新类，这种功能只有在编译时无法确定需要实现哪个接口时才有必要使用

### 6.5.1 何时使用代理

代理类可以在运行时创建全新的类，这样代理类能够实现指定的接口，尤其是，它具有下列方法：

* 指定接口所需要的全部方法
* `Object`类中的全部方法，例如，`toString`、`equals`等

然而不能在运行时定义这些方法的新代码。而是要提供一个调用处理器(invoation handler)。调用处理器是实现了InvocationHandler接口的类对象。在这个接口中只有一个方法：

```java
Object invoke(Object proxy, Method method, Obejct[] args)
```

无论何时调用代理对象的方法，调用处理器的`invoke`方法都会被调用，并向其传递`Method`对象和原始的调用参数。调用处理器必须给出处理调用的方式。

### 6.5.2 创建代理对象

要想创建一个代理对象，需要使用`Proxy`类的`newProxyInstance`方法。这个方法有三个参数：

* 一个类加载器(class loader)
* 一个`Class`对象数组，每个元素都是需要实现的接口
* 一个调用处理器

使用代理机制可以解决什么问题：

* 路由对远程服务器的方法调用
* 在程序运行期间，将用户接口事件与动作关联起来
* 为调试，跟踪方法调用

以下示例程序中，使用代理和调用处理器跟踪方法调用，并且定义了一个`TraceHander`包装器类存储包装的对象。其中`invoke`方法打印出被调用方法的名字和参数，随后用包装好的对象作为隐式参数调用这个方法，完整的代码`chap6/Proxy/ProxyTest.java`

```java
package chap6.proxy;

import java.lang.reflect.*;
import java.util.*;

public class ProxyTest
{
    public static void main(String[] args){
        Object[] elements = new Object[1000];

        for(int i = 0; i < elements.length; i++){
            Integer value = i + 1;
            InvocationHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[] {Comparable.class}, handler);
            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length) + 1;

        int result = Arrays.binarySearch(elements, key);

        if(result >= 0) System.out.println(elements[result]);

    }
}

class TraceHandler implements InvocationHandler
{
    private Object target;

    public TraceHandler(Object t){
        target = t;
    }

    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
    {
        System.out.print(target);
        System.out.print("." + m.getName() + "(");
        if(args != null){
            for(int i = 0; i < args.length; i++){
                System.out.print(args[i]);
                if(i < args.length - 1) System.out.println(", ");
            }
        }
        System.out.println(")");
        return m.invoke(target, args);
    }
}
```

### 6.5.3 代理类的特性

代理类是在程序运行过程中创建的，然而，一旦被创建，就变成了常规类，与虚拟机的任何其他类没有什么区别





















