# 第8章 泛型程序设计

泛型机制编写的程序代码要比那些杂乱地使用`Object`变量，然后进行酱紫类型转换的代码具有更好的安全性和可读性。泛型对于集合类尤其有用，例如，`ArrayList`就是一个无处不在的集合类。从表面上来看，泛型很像`C++`中的模板。

## 8.1 为什么要使用泛型程序设计

泛型程序设计(Generic programming)意味着编写的代码可以被很多不同类型的对象所重用。

### 8.1.1 类型参数的好处

泛型提供了一个类型参数(type parameters)，可以用来指示元素的类型

```java
ArrayList<Sring> files = new ArrayList<String>();
```

这样使得代码具有更好的可读性

## 8.2 定义简单泛型类

一个泛型类(generic class)就是具有一个或多个类型变量的类，例如以下一个`Pair`类的代码

```java
package chap8.pair1;

public class Pair<T> {
    private T first;
    private T second;

    public Pair() { first = null; second = null;}
    public Pair(T first, T second) {this.first = first; this.second = second;}

    public T getFirst() { return first; }
    public T getSecond() { return second; }

    public void setFirst(T newValue) { first = newValue; }
    public void setSecond(T newValue) { second = newValue; }
}
```

`Pair`类引入了一个类型变量`T`，用尖括号`<>`括起来，并放在类名的后面。泛型类可以有多个类型变量

```java
public class Pair<T, U> {...}
```

类定义中的类型变量指定方法的返回类型以及与和局部变量的类型

```java
private T first;
```

用具体的类型替换类型变量就可以实例化泛型类型

```java
Pair<String>
```

## 8.3 泛型方法

```java
class ArrayAlg
{
	public static <T> T getMiddle(T... a)
	{
		return a[a.length/2];
	}
}
```

这个方法是在普通类中定义的，而不是在泛型类中定义的。然而，这是一个泛型方法。注意，类型变量放在修饰符(这里是`public static`)的后面，返回类型的前面

> 泛型方法可以定义在普通类中，也可以定义在泛型类中

调用一个泛型方法是，在方法名前的前括号中放入具体的类型

```java
String middle = ArrayAlg.<String>getMiddle("Joho", "Q.", "Public");
```

大多数情况下，可以省略`<String>`类型参数，编译器有足够的信息能够推断所调用的方法。但是有的时候，编译器也会提示错误

```java
double middle = ArrayAlg.getMiddle(3.14, 1729, 0);
```

编译器会自动打包参数为1个`Double`和2个`Integer`对象，而后寻找这些类的共同超类型，找到`Number`和`Comparable`接口，其本身也是一个泛型类型。在这种情况下，可以采取的补救措施是将所有的参数写为`double`值

## 8.4 类型变量的限定

有的时候，类或方法需要对类型变量加以约束，例如，需要类型变量实现了`CompareTo`方法，解决方案是将类型变量限制为实现了`Comparable`接口的类

```java
class ArrayAlg
{
    public static <T extends Comparable> T min(T[] a)
    {
        if (a==null || a.length == 0) return null;
        T smallest = a[0];
        for(int i = 1; i < a.length; i++)
            if(smallest.compareTo(a[i]) > 0) smallest = a[i];
        return smallest;
    }
}
```

这里为什么使用`extends`而不是`implements`，毕竟`Comparable`是一个接口，可以理解为：

```java
<T extends BoudingType>
```

这里的`T`应该是绑定类型的值类型(subtype)。`T`和绑定类型可以是类，也可以是接口。选择关键词`extends`的原因是更接近子类的概念，并且`Java`的设计者也不打算在语言中再添加一个新的关键字(如`sub`)

一个类型变量或者通配符可以有多个限定，例如

```java
T extends Comparable & Serializable
```

限定类型用`&`分隔，而逗号用来分隔类型变量

利用泛型重新编写了一个`minmax`，这个方法计算泛型数组的最大值和最小值，并返回`Pair<T>`

`pair2/PairTest2.java`

```java
package chap8.pair2;

import java.time.LocalDate;

public class PairTest2 {
    public static void main(String[] args)
    {
        LocalDate[] birthdays = 
        {
            LocalDate.of(1906, 12, 9),
            LocalDate.of(1815, 12, 10),
            LocalDate.of(1903, 12, 3),
            LocalDate.of(1910, 6, 22),
        };
        Pair<LocalDate> mm = ArrayAlg.minmax(birthdays);
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());
    }
}
class ArrayAlg
{
    public static <T extends Comparable> Pair<T> minmax(T[] a)
    {
        if(a == null || a.length == 0) return null;
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.compareTo(a[i]) > 0) min = a[i];
            if (max.compareTo(a[i]) < 0) max = a[i];
        }
        return new Pair<>(min, max);
    }
}

```

## 8.5 泛型代码和虚拟机

虚拟机没有泛型类型对象——所有对象都属于普通类

### 8.5.1 类型擦除

无论何时定义一个泛型类型，都自动提供了一个相应的原始类型。原始类型的名字就是删除类型参数后的泛型类型名。擦除类型变量，并替换为限定类型。

### 8.5.2 翻译泛型表达式

如果程序调用泛型方法时，如果擦除返回类型，编译器插入强制类型转换。

```java
Pair<Employee> buddies = ...;
Employee buddy = buddies.getFirst();
```

### 8.5.3 翻译泛型方法

```java
class DateInterval extends Pair<LocalDate>
{
	public void setSecond(LocalDate second)
    {
        if(second.compareTo(getFirst()) >= 0)
            super.setSecond(second);
    }
}
```

如上代码，一个日期区间是一对`LocalDate`对象，并且需要覆盖这个方法来确保第二个值永远不小于第一个值，擦除之后可能变为

```java
class DateInterval extends Pair
{
    public void setSecond(LocalDate second) { ... }
    ...
}
```

 令人奇怪的是，存在另一个从`Pair`继承的`setSecond`方法，即

```java
public vodi setSecond(Object second)
```

这里希望对`setSecond`的调用具有多态性，编译器会生成一个*桥方法(bridge method*：

```java
public void setSecond(Object second){ setSecond((Date) second); }
```

### 8.5.4 调用遗留代码

设计`Java`泛型类型是，主要目标是允许泛型代码和遗留代码之间能够相互操作。

```java
Dictionary<Integer, Component> labelTable = new Hashtable<>();
labelTable.put(0, new JLabel(new ImageIcon("nine.gif")));
labelTable.put(20, new JLabel(new ImageIcon("ten.gif")));
```

将`Dictionary<Integer, Component>`对象传递给`setLabelTable`时，编译器会发出一个警告

```java
slider.setLabelTable(labelTable); //Warning
```

在查看了警告之后，可以利用注解(annotation)使之消失，注释必须放在生成这个警告的代码所在的方法之前，如下：

```java
@SuppressWarnings("unchecked")
Dictionary<Integer, Components> labelTable = slider.getLabelTable(); // No warning
```



