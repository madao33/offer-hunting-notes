# 第4章 对象与类

## 4.3 用户自定义类

### 4.3.3 剖析Employee类

```java
class Employee
{
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String n, double s, int year, int month, int day)
    {
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }

    public String getName()
    {
        return name;
    }

    public double getSalary()
    {
        return salary;
    }

    public LocalDate getHireDay()
    {
        return hireDay;
    }

    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
```

* 这个`Employee`类包含一个构造器和四个方法，类的所有方法都标记为`public`，这意味着任何方法都可以调用这些方法

* `Employee`类的实例中有三个实例域用来存放将要操作的数据

  ```java
  private String name;
  private double salary;
  private LocalDate hireDay;
  ```

  `private`确保了只有`Employee`类自身的方法能够访问这些实例域，而其他类的方法不能读写这些域

### 4.3.4 从构造器开始

```java
public Employee(String n, double s, int year, int month, int day)
{
    name = n;
    salary = s;
    hireDay = LocalDate.of(year, month, day);
}
```

* 构造器和类同名，在构造`Employee`类的对象时，构造器会运行，以便将实例域初始化为所希望的状态
* 构造器和其他的方法有一个重要的不同，构造器总是伴随着`new`操作符的执行被调用，而不能对一个已经存在的对象调用构造器来达到重新设置实例域的目的

构造器的其他特性：

* 构造器与类同名
* 每个类可以有一个以上的构造器
* 构造器可以有0个、1个或多个参数
* 构造器没有返回值
* 构造器总是伴随着`new`操作一起调用

> Java对象都是在堆中构造的，构造总是伴随着`new`操作符一起使用

### 4.3.5 隐式参数与显式参数

```java
double raise = number007.raiseSalary(5);
```

`raiseSalary`方法有两个参数：

* 第一个为隐式（implicit）参数，就是出现在方法名前的`Employee`类对象
* 第二个参数为显式（explicit）参数，位于方法名后面括号中的数字

在类中的方法中，关键词`this`表示隐式参数

```java
public void raiseSalary(double byPercent)
{
	double raise = this.salary * byPercent / 100;
	this.salary += raise;
}
```

### 4.3.6 封装的优点

可以确保实例域不被外界破坏

> 不要写返回可变对象的访问器方法，如果需要返回一个可变对象的引用，应该首先对它进行克隆（clone），对象clone是指存放在另一台位置上的对象副本

```java
class Employee
{
	...
	public Date getHireDay()
	{
		return (Data) hireDay.clone();
	}
}
```

### 4.3.7 基于类的访问权限

方法可以访问所调用对象的私有数据，并且一个方法可以访问所属类的所有对象的私有数据

```java
class Employee
{
	...
	public boolean equals(Employee other)
	{
		return name.equals(other.name);
	}
}
```

### 4.3.8 私有方法

* 一般数据域设置为私有的
* 在一些特殊情况下，一些方法设计为私有的，因为一些代码设计为若干独立的辅助方法，这些辅助方法设计为`private`
* 如果方法从私有的，那么设计者可以认为它不会被外部的其他类操作调用，可以将其删去，如果方法是共有的，就不能将其删去，因为其他的代码很可能依赖它

### 4.3.9 final实例域

`final`在每一个构造器执行之后，这个域的值被设置，并且在后面的操作中，不能够再对它进行修改

例如`Employee`类中的`name`域声明为`final`，因为在对象构建之后，这个值不会再被修改，机没有`setName`方法

```java
class Employee
{
	private final String name;
	...
}
```

## 4.4 静态域与静态方法

`main`方法都被标记为`static`修饰符

### 4.4.1 静态域

如果将域定义为`static`，每个类中只有一个这样的域。而每一个对象对于所有的实例域却都有自己的一份拷贝。

```java
class Employee
{
	private static int nextId = 1;	
	private int id;
	...
    public void setId()
    {
        id = nextId;
        nextId++;
    }
}
```

该类的每一个雇员对象都有一个自己的id域，但这个类的所有实例将共享一个`nextId`域

* 静态域`nextId`属于类，不属于任何独立的对象
* 即使没有一个雇员对象，静态域`nextId`也存在

### 4.4.2 静态常量

静态变量用的比较少，静态常量使用的比较多，可以设置为`public`

```java
public class math
{
	...
	public static final double PI = 3.141592657;
}
```

### 4.4.3 静态方法

静态方法是一种不能向对象实施操作的方法，即没有隐式的参数

```java
public static int getNextId()
{
	return nextId;
}
```

* `Employee`类的静态方法不能访问`Id`实例域，因为他不能操作对象，但是静态方法可以访问自身类中的静态域

* 可以使用对象调用静态方法，但是容易造成混淆，所以还是建议使用类名来调用静态方法

  ```java
  int n = Employee.getNextId();
  ```



## 4.5 方法参数

Java程序设计语言总是采用按值调用

* 一个方法不能修改一个基本数据类型的参数（即数值型或布尔型）
* 一个方法可以改变一个对象参数的状态
* 一个方法不能让对象参数引用一个新的对象

`ParamTest.java`

```java
public class ParamTest {
    public static void main(String[] args){

        /* 
         * Test1: Methods can't modify numeric parameters   
         */
        System.out.println("Testing tripleValue: ");
        double percent = 10;
        System.out.println("Before: percent = " + percent);
        tripleValue(percent);
        System.out.println("After: percent = " + percent);

        /*
         * Test2: Methods can change the state of object parameters
         */
        System.out.println("\nTesting tripleSalary:");
        Employee harry = new Employee("Harry", 50000);
        System.out.println("Before: salary = " + harry.getSalary());
        tripleSalary(harry);
        System.out.println("After: salary = " + harry.getSalary());

        /*
         * Test3: Methods can't attach new objects to object parameters
         */ 
        System.out.println("\nTesting Swap:");
        Employee a = new Employee("Alice", 70000);
        Employee b = new Employee("Bob", 60000);
        System.out.println("Before: a = " + a.getName());
        System.out.println("Before: b = " + b.getName());
        swap(a, b);
        System.out.println("After: a = " + a.getName());
        System.out.println("After: b = " + b.getName());
    }
    
    public static void tripleValue(double x)
    {
        x = 3 * x;
        System.out.println("End of the method: x = " + x);
    }

    public static void tripleSalary(Employee x)
    {
        x.raiseSalary(200);
        System.out.println("End of the method: salary = " + x.getSalary());
    }

    public static void swap(Employee x, Employee y)
    {
        Employee temp = x;
        x = y;
        y = temp;
        System.out.println("End of method: x = " + x.getName());
        System.out.println("End of method: y = " + y.getName());
    }

}

class Employee
{
    private String name;
    private double salary;

    public Employee(String n, double s)
    {
        name = n;
        salary = s;
    }

    public String getName()
    {
        return name;
    }

    public double getSalary()
    {
        return salary;
    }

    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}

```

## 4.6 对象构造

### 4.6.1 重载

出现多个方法有相同的名字、不同的参数，便产生了重载

编译器根据各个方法给出的恩参数类型与特定方法调用说使用的值类型进行匹配来挑选出相应的方法，如果编译器找不到匹配的参数，就会产生编译错误，这个过程叫做**重载解析（overloading resolution）**

> 不只是构造器，Java允许重载任何方法，完整的描述一个方法，需要指出方法名以及参数类型，这叫做方法的签名（signature）

### 4.6.2 默认域初始化

构造器没有显式地赋予初值，那么就会自动地赋为默认值，建议还是赋予初值，不然会出现`getName`方法等为`null`引用

```java
LocalDate h = harry.getHireDay();
int year = h.getYear(); // throws exception if h is null
```

### 4.6.3 无参数的构造器

* 如果编写一个类时没有编写构造器，那么系统就会提供一个无参数构造器，这个构造器将所有的实例域设置为默认值
* 如果类中提供了至少一个构造器，但是没有提供无参数的构造器，则在构造对象时如果没有提供参数就会被视为不合法

### 4.6.5 参数名

可以使用`this`指示隐式参数

```java
public Employee(String name, double salary)
{
	this.name = name;
	this.salary = salary;
}
```

### 4.6.6 调用另一个构造器

构造器的第一个语句形如`this(...)`，这个构造器将调用同一个类的另一个构造器

```java
public Employee(double s)
{
	this("Employee #" + nextId, s);
	nextId++;
}
```

采用这种方式使用`this`关键词非常有用，这样对公共的构造器代码部分只编写一次即可。

### 4.6.7 初始化块

前面的两种初始化数据域的方法：

* 在构造器中设置值
* 在声明中赋值

实际上，Java还有第三种机制，称为*初始化块*（initialization block）。在一个类的声明中，可以包含多个代码块，只要构造类的对象，这些块就会被执行

```java
class Employee
{
    private static int nextId;

    private int id;
    private String name = "";
    private double salary;
    
	static
    {
        Random generator = new Random();
        nextId = generator.nextInt(10000);
    }
    
    {
        id = nextId;
        nextId++;
    }
}
```

> 在这个示例中，无论使用哪个构造器构造对象，`id`域都在初始化块中被初始化。首先运行初始化块，然后才运行构造器的主体部分。

调用构造器的具体处理步骤：

1. 所有数据域被初始化为默认值(0、false或null)
2. 按照在类声明中出现的次序，依次执行所有域初始化语句和初始化块
3. 如果构造器第一行调用了第二个构造器，则执行第二个构造器主体
4. 执行这个构造器主体

如果对类的静态域进行初始化的代码比较复杂，那么可以使用静态的初始化块

```java
static
{
    Random generator = new Random();
    nextId = generator.nextInt(10000);
}
```

在类第一次加载的时候，将会进行静态域的初始化

`ConstructorTest.java`

```java
package chap4;

import java.util.*;

public class ConstructorTest{
    public static void main(String[] args)
    {
        Employee1[] staff = new Employee1[3];

        staff[0] = new Employee1("Harry", 4000);
        staff[1] = new Employee1(60000);
        staff[2] = new Employee1();

        for(Employee1 e : staff)
            System.out.println("name = " + e.getName() + ", id = " + e.getId() + ", salary = " + e.getSalary());
    }
    
}

class Employee1
{
    private static int nextId;

    private int id;
    private String name = "";
    private double salary;

    static
    {
        Random generator = new Random();
        nextId = generator.nextInt(10000);
    }

    {
        id = nextId;
        nextId++;
    }

    public Employee1(String n, double s)
    {
        name = n;
        salary = s;
    }

    public Employee1(double s)
    {
        this("Employee #" + nextId, s);
    }

    public Employee1()
    {

    }

    public String getName()
    {
        return name;
    }

    public double getSalary()
    {
        return salary;
    }

    public int getId()
    {
        return id;
    }
}
```

## 4.7 包

### 4.7.1 类的导入

可以使用两种方式访问另一个包中的公有类：

* 第一种方法是在每个类名之前添加完整的包名

  ```java
  java.time.LocalDate today = java.time.LocalDate.now();
  ```

* 简单常用的方式是使用`import`语句，`import`语句是一种引用包含在包中的类的简明描述

  ```java
  import java.util.*;
  
  
  ...
  LocalDate today = LocalDate.now();
  ```

如果类名相同，在每个类名的前面加上完整的包名

```java
import java.sql.*;
import java.util.Date;

java.util.Date deadline = new java.util.Date();
jave.sql.Date today = new java.sql.Date();
```

### 4.7.2 静态导入

`import`语句不仅可以导入类，还增加了导入静态方法和静态域的功能

```java
import static java.lang.System.*;

out.println("Goodbye, world!");
exit(0);
```

> 如上，导入了`System`类的静态方法和静态域，而不必加类名前缀

### 4.7.3 将类放入包中

要想将一个类放入包中，就必须将包的名字放在源文件的开头

```java
package com.horstmann.corejava;

public class Employee
{
	... ...
}
```

将包中的文件放到与完整的包名匹配的子目录中，例如上述代码放置在`com/horstmann/corejava`目录下

### 4.7.4 包作用域

* 标记为`public`的部分可以被任意的类使用
* 标记为`private`的部分只能被定义它们的类使用
* 如果没有指定`public`或`private`，这个部分（类、方法或变量）可以被同一个包中的所有方法访问。

## 4.8 类路径

包树状结构基目录为`classdir`，`jar`文件路径在`archives`

设置类的路径可以使用`-classpath`或`-cp`选项指定类路径

```shell
// UNIX
java -classpath classdir:.:archives

// Windows
java -classpath classdir;.;archives
```

## 4.9 文档注释

在源代码添加专用的定界符`/** */`开始的注释，就可以生成一个专业水准的文档，使用`javadoc`命令就可以完成了

### 4.9.2 类注释

类注释必须放在`import`语句之后，类定义之前

```java
/**
 * A test class
 */
class Employee
{
    private String name;
    private double salary;
    ...
}
```

### 4.9.3 方法注释

方法注释除了通用标记，还可以使用下面的标记

* `@param` 变量描述，这个描述可以占据多行，并可以使用HTML标记，一个方法的所用`@param`标记必须放在一起
* `@return`描述，可以跨越多行，并且可以使用HTML标记
* `@throws`描述，添加一个注释，用于表示这个方法可能抛出异常

```java
/**
* Raises the salary of an employee.
* @param byPercent the percentage by which to raise the salary(e.g. 10 means 10%).
* @return the amount of the raise
*/
public double raiseSalary(double byPercent)
{
    double raise = salary * byPercent / 100;
    salary += raise;
    return raise;
}
```

### 4.9.4 域注释

只需要对公有域（通常是指静态变量）建立文档

```java
/**
 * The "Hearts" card suit
 */
private static int nextId;
```

### 4.9.5 通用注释

* `@author` 可以使用多个`@author`标记

* `@version` 对当前版本的描述

* `@since` 对引入特性的版本描述

* `@deprecated` 对类、方法或变量添加一个不再使用的注释

* `@see` 超级链接

  `@see com.horstmann.corejava.Employee#raiseSalary(double)`

  `@see <a hred="www.baidu.com">Baidu main page</a>`

  `@see "Core Java 2 volume 2"`

* 如果愿意的话，可以在注释中的任何文字指向其他类或方法的超级链接，以及插入一个专用的标记

  `{@link package.class#feature label}`

### 4.9.7 注释的提取

1. 切换到包含想要生成文档的源文件目录

2. 如果是一个包，运行命令

   `javadoc -d docDirectory nameOfPackage`

   对于多个包生成文档

   `javadoc -d docDirectory nameOfPackage nameOfPackage2`

   如果文件在默认包中

   `javadoc -d docDirectory *.java`

## 4.10 类设计技巧

* 一定要保证数据私有
* 一定要对数据初始化
* 不要在类中使用过多的基本类型
* 不是所有的域都需要独立的域访问器和域更改器
* 将职责过多的类进行分解
* 类名和方法名要能够体现他们的职责
* 优先使用不可变的类

