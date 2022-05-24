# Java进阶简介

主要的知识点来自于黑马程序员的视频：`BV1TE41177mP`

[TOC]

# day1-复习回顾、静态、继承、引用类型使用

## 定义类

- 类名的首字母应该大写，满足**驼峰写法**
- 一个Java文件可以定义多个类。但是只有一个类是用`public`修饰，`public`修饰的类名必须称为`Java`文件名。

- 类中有且仅有5大成分（五大金刚）

  - **成员变量Field**：描述类或者对象的属性信息的。
  - **成员方法Method**：描述类或者对象的行为的。
  - **构造器（构造方法,Constructor）**: 初始化类的一个对象返回。
    - 有参构造器
    - 无参构造器
  - **代码块**

  - **内部类**

## 封装

- 面向对象的三大**特征**之一：**封装，继承，多态**。 
- 形成了规范，即使毫无意义还是会这样写代码！
- 合理隐藏，合理暴露。
- 封装的规范：成员变量私有，方法一般公开，提供成套的`getter`和`setter`方法暴露成员变量的取值和赋值，`public`修饰符
- 封装的作用：提高安全性，提高代码的组件化思想。
- 封装已经成为`Java`代码的规范，即使毫无意义，我们也要这样写代码（成员变量私有，方法公开）

## this关键字

- `this`代表了当前对象的引用。
- `this`可以出现在构造器和方法中。
- `this`出现在构造器中代表构造器正在初始化的对象。
- `this`出现在方法中，哪个对象调用方法，`this`就代表哪个对象。
- `this`可以访问对象的成员变量，区分成员变量是局部的还是对象中的成员变量。

## static关键字

`Java`是通过成员变量是否有`static`修饰来区分是类的还是属于对象的

* 没有`static`修饰的方法和变量是属于每个对象的
* 有`static`修饰的方法和成员变量属于类的

按照有无`static`修饰，成员变量和方法可以分为：

**成员变量**：

* **静态成员变量**（类变量）：有`static`修饰的成员变量称为静态成员变量也叫类变量，属于类本身的，**直接用类名访问**即可。
* **实例成员变量**：无`static`修饰的成员变量称为实例成员变量，属于类的每个对象的，**必须用类的对象来访问**。

> * 同一个类中访问静态成员变量可以省略类名不写
> * 对象也可以访问静态成员变量，但是不推荐，静态成员变量属于类，如果用对象访问静态成员变量容易混淆

成员变量访问内存

![image-20220427112225577](imgs/image-20220427112225577.png)

```java
public class Student{
    // 1.静态成员变量:有static修饰，属于类本身，直接用类名访问即可！
    public static String schoolName = "黑马";
    // 2.实例成员变量:无static修饰，属于类的对象的，必须用对象访问！
    private String name;
    private int age ;

    public static void main(String[] args) {
        // 1.类名.静态成员变量
        System.out.println(Student.schoolName);
        // 注意：同一个类中访问静态成员变量可以省略类名不写
        System.out.println(schoolName);

        // 2.对象.实例成员变量
        //System.out.println(Student.name); // 报错！
        Student swk = new Student();
        swk.name = "孙悟空";
        System.out.println(swk.name);
        System.out.println(swk.age);

        // 3.对象.静态成员变量(不推荐)
        // 静态成员变量属于类，直接用类名访问即可。
        System.out.println(swk.schoolName);
    }
}
```

**成员方法**：

* **静态方法**：有`static`修饰的成员方法称为静态方法也叫类方法，属于类本身的，**直接用类名访问**即可。
* **实例方法**：无`static`修饰的成员方法称为实例方法，属于类的每个对象的，**必须用类的对象**来访问。

> * 静态方法属于类，有static修饰，直接用类名访问即可。
> * 实例方法属于对象，无static修饰，必须先创建对象，然后用对象来访问。
> * 静态方法也可以被对象共享访问，但是不推荐，因为静态方法直接用类名访问即可。

![image-20220427143750503](imgs/image-20220427143750503.png)

```java
public class Student {
    // 0.实例成员变量。
    private String name;
    private int age ;

    // 1.静态方法：有static修饰，属于类，直接用类名访问即可！
    public static void inAddr(){
        System.out.println("我们都在天河区吉山村happy的学习Java!");
    }

    // 2.实例方法：无static修饰，属于对象，必须用对象访问！
    public void eat(){
        System.out.println(name + "已经"+age+"岁，在吃好吃的！！");
    }

    public static void main(String[] args) {
        // a.类名.静态方法
        Student.inAddr();
        // 注意：在同一个类中访问静态成员可以省略类名不写！！
        inAddr();

        // b.对象.实例方法
        // Student.eat(); // 报错了！
        Student zbj = new Student();
        zbj.name = "猪刚鬣";
        zbj.age = 1000;
        zbj.eat();

        // c.对象.静态方法(不推荐)
        zbj.inAddr();
    }
}
```

关于`static`常考的八类题：

* 实例方法是否可以直接访问实例成员变量？可以的，因为它们都属于对象。
* 实例方法是否可以直接访问静态成员变量？可以的，静态成员变量可以被共享访问。
* 实例方法是否可以直接访问实例方法? 可以的，实例方法和实例方法都属于对象。
* 实例方法是否可以直接访问静态方法？可以的，静态方法可以被共享访问！
* 静态方法是否可以直接访问实例变量？ 不可以的，实例变量必须用对象访问！！
* 静态方法是否可以直接访问静态变量？ 可以的，静态成员变量可以被共享访问。
* 静态方法是否可以直接访问实例方法? 不可以的，实例方法必须用对象访问！！
* 静态方法是否可以直接访问静态方法？可以的，静态方法可以被共享访问！！

> **也就是说实例方法啥都可以访问，静态方法只能访问静态方法或者静态变量**

## 继承

### 继承的概述

面向对象的三大特征：封装、继承和多态

继承是Java中一般到特殊的关系，是一种子类到父类的关系。例如：学生类继承了人类。  猫类继承了动物类。

被继承的类称为：父类/超类。继承父类的类称为：子类

* 继承可以**提高代码的复用性**
* 子类直接继承父类，就可以直接使用父类的这些代码了（相同代码重复利用）

子类继承了一个父类，子类就可以直接得到父类的属性（成员变量）和行为（方法）了。

### 继承的例子

```java
class Animal{

}

class Cat extends Animal{

}
```

> - 继承的优势可以把相同的代码定义在父类中，子类可以直接继承使用。
> - 这样就可以**提高代码的复用性**：相同代码只需要在父类中写一次就可以了。

### 子类不能继承父类的内容

- 子类继承父类，子类就得到了父类的属性和行为。
- 但是并非所有父类的属性和行为等子类都可以继承。

**子类不能继承父类的东西**：子类不能继承父类的构造器，子类有自己的构造器。（没有争议的）

有争议的观点（拓展）：

**子类是否可以继承父类的私有成员**（私有成员变量，私有成员方法）?

* **子类是可以继承父类的私有成员的，只是不能直接访问而已**。
* 以后可以暴力去访问继承自父类的私有成员~~~

**子类是否可以继承父类的静态成员？**

* **子类是不能继承父类的静态成员的**
* **子类只是可以访问父类的静态成员**，父类的静态成员只有一份可以被子类共享访问。
* **共享并非继承**

### 成员变量的访问特点

**就近原则**：子类有找子类，子类没有找父类，父类没有就报错

```java
class Animal{
    public String name = "动物名称";
}

class Cat extends Animal{
    public String name = "子类名称";
    public void show(){
        String name = "局部名称";
        System.out.println(name); // 局部名称
        System.out.println(this.name); // 子类名称
        System.out.println(super.name); // 父类名称
    }
}
```

> - `this`代表了当前对象的引用，可以用于访问当前子类对象的成员变量。
> - `super`代表了父类对象的引用，可以用于访问父类中的成员变量。

### 成员方法的访问特点

就近原则：子类有找子类，子类没有找父类，父类没有就报错

子类对象优先使用子类已有的方法，也就是说父类的方法被重写

```java
public class TestDemo {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.run(); // 子类的
        cat.eat(); // 父类的
        // cat.go(); // 报错！
    }
}

class Animal{
    public void run(){
        System.out.println("动物可以跑~~~~");
    }

    public void eat(){
        System.out.println("吃东西~~~~");
    }
}

class Cat extends Animal {
    public void run(){
        System.out.println("🐱跑的贼溜~~~~");
    }
}
```

### 方法重写

子类继承了父类，子类就得到了父类的某个方法。但是子类觉得父类的这个方法不好用或者无法满足自己的需求，子类重写一个与父类申明一样的方法来覆盖父类的该方法，子类的这个方法就进行了方法重写。

方法重写的校验注解： `@Override`

- `Java`建议在重写的方法上面加上一个`@Override`注解。
- 方法一旦加了这个注解，那**就必须是成功重写父类**的方法，否则报错！
- `Override`优势：**可读性好，安全，优雅**

方法重写的具体要求：

* 子类重写方法的**名称和形参列表必须与父类被重写方法一样**。
* 子类重写方法的返回值类型申明要么与父类一样，要么比父类方法**返回值类型范围更小**。（以后再了解）
* 子类重写方法的修饰符权限应该与父类被重写方法的**修饰符权限相同或者更大**。（以后再了解）
* 子类重写方法申明抛出的异常应该与父类被重写方法申明抛出的**异常一样或者范围更小**！（以后再了解）

```java
class Wolf extends Animal{
    // 进行了方法重写！！
    // 子类重写方法的名称和形参列表必须与父类被重写方法一样
    // 子类重写方法的返回值类型申明要么与父类一样，要么比父类方法返回值类型范围更小
    // 子类重写方法的修饰符权限应该与父类被重写方法的修饰符权限相同或者更大
    @Override
    public void run(){
        System.out.println("🐺跑的贼快~~~");
    }
}

class Animal{
    public void run(){
        System.out.println("动物可以跑步~~~");
    }
}
```

> - 方法重写是子类重写一个与父类申明一样的方法覆盖父类的方法。
> - 方法重写建议加上`@Override`注解。
> - 方法重写的核心要求：方法名称形参列表必须与被重写方法一致！！
> - 建议**申明不变，重新实现**。

调用父类被重写的方法使用`super`

```java
class SportMan extends People{
    @Override
    public void run(){
        System.out.println("运动员跑的贼快~~~~~");
    }

    public void go(){
        super.run(); // 父类被重写的方法
        run(); // 子类的
    }
}

class People{
    public void run(){
        System.out.println("人会跑~");
    }
}
```

> `super`可以用在子类的实例方法中调用父类被重写的方法

静态方法和私有方法**不可以**被重写

```java
class Mac extends Computer{
//    @Override
    public void go(){
    }

    // @Override
    public static void test(){
    }
}

class Computer{
    public static void test(){
        System.out.println("super test");
    }

    private void go(){

    }
}
```

### 继承后构造器的特点

子类的全部构造器默认一定会**先访问父类的无参数构造器，再执行子类自己的构造器**，主要的原因是

* 子类的构造器的第一行默认有一个`super()`调用父类的无参数构造器，写不写都存在
* 子类继承父类，子类就得到了父类的属性和行为
* 当我们调用子类构造器初始化子类对象数据的时候，必须先调用父类构造器初始化继承自父类的属性和行为

### super调用父类构造器

`super(...)`可以根据参数选择调用父类的某个构造器

```java
class Monkey extends Animal{

    public Monkey(String name, int age, char sex) {
        super(name , age , sex) ; // 根据参数匹配调用父类构造器
    }

    public void eatBanana(){
        System.out.println(getName()+"-->"+getAge()+"-->"+getSex()+"在吃🍌~~~");
    }
}

class Animal{
    private String name;
    private int age;
    private char sex;

    public Animal() {
    }

    public Animal(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
```

`super`调用父类构造器的内存分布图

![image-20220505160400907](imgs/image-20220505160400907.png)

### this和super关键字使用总结

`this`代表了当前对象的引用（继承中指代子类对象）：

* `this` 子类成员变量
* `this`子类成员方法
* `this(...)` 可以根据参数匹配访问本类其他构造器

`super`代表了父类对象的引用（继承中指代了父类对象空间）

* `super` 父类成员变量
* `super`父类的成员方法
* `super(...)`可以根据参数匹配访问父类的构造器

`this(...)`和`super(...)`**必须放在构造器的第一行**，否则报错

所以`this(...)`和`super(...)`**不能同时出现在构造器中**

```java
class Student{
    private String name ;
    private int age ;
    private String schoolName ;

    public Student() {
    }

    public Student(String name , int age){
        // 借用兄弟构造器的功能！
        this(name , age , "黑马");
    }

    public Student(String name, int age, String schoolName) {
        this.name = name;
        this.age = age;
        this.schoolName = schoolName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
```

### 继承的特点

* **单继承**：一个类只能继承一个直接父类

  * 如果是多继承可能会出现类的**二义性**

    ```java
    class A{
        public void test(){
            System.out.println("A");
        }
    }
    class B{
        public void test(){
            System.out.println("B");
        }
    }
    class C extends A , B {
        public static void main(String[] args){
            C c = new C();
            c.test(); // 出现了类的二义性！所以Java不能多继承！！
        }
    }
    ```

* **多层继承**：一个类可以间接继承多个父类

* 一个类可以有多个子类

* 一个类要么默认继承了`Object`类，要么间接继承了`Object`类，`Object`类是`Java`的祖宗类

## 引用类型作为方法参数和返回值

* 除了基本数据类型都是引用数据类型
* 引用类型可以作为方法的参数类型和返回值类型
* 引用数据类型可以在一切可以使用类型的地方使用

```java
public class TestDemo {
    public static void main(String[] args) {
        Dog jinMao = new Dog();
        go(jinMao);

        System.out.println("--------------");
        Dog dog = createDog();
        dog.run();
    }

    // 引用类型作为方法的返回值:创建一个狗对象返回！
    public static Dog createDog(){
//        Dog taiDi = new Dog();
//        return taiDi;
         return new Dog();
    }

    // 引用类型作为方法参数: 提供一个方法让狗进入比赛~~~
    public static void go(Dog a){
        System.out.println("比赛开始。。。");
        a.run();
        System.out.println("比赛结束。。。");
    }
}

class Dog{
    public void run(){
        System.out.println("🐕跑的贼溜~~~");
    }
}
```

## 引用类型作为成员变量的类型

`Address.java`

```java
public class Address {
    private String code;
    private String name;
    private double x;
    private double y;

    public Address() {
    }

    public Address(String code, String name, double x, double y) {
        this.code = code;
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
```

`Student.java`

```java
public class Student {
    private String name;
    private int age ;
    // 地址信息:复合类型。
    // 引用类型作为成员变量的类型
    private Address address;
}
```

# day2-抽象类、接口、代码块、final、单例、枚举

## 抽象类

### 抽象类的概述

父类指导之类一定要完成某个功能，但是每个之类完成的情况是不一样的。子类以后也只会用自己重写的功能，那么父类的该功能就可以定义成抽象方法，子类重写调用自己的方法。所以父类的该功能就可以定义为抽象的方法。拥有冲向方法的类必须定义为抽象类。

> **抽象方法**：没有方法体，只有方法签名，必须用`abstract`修饰的方法就是抽象方法。
>
> **抽象类**：拥有抽象方法的类必须定义成抽象类，必须用`abstract`修饰。

```java
class Wolf extends Animal{
    @Override
    public void run(){
        System.out.println("🐺跑的贼贼溜~~~");
    }
}

// 抽象类：拥有了抽象方法的类必须定义成抽象类。抽象类必须加上abstract修饰。
abstract class Animal{
    // 抽象方法：没有方法体，只有方法签名，必须加上abstract修饰。
    public abstract void run();
}
```

### 抽象类的使用

抽象类是为了被子类继承

```java
class Manager extends Employee{
    @Override
    public void work() {
        System.out.println("班主任需要管理班级~~~~");
    }

     @Override
     public void run() {

     }
 }

class Techer extends Employee{
    @Override
    public void work() {
        System.out.println("老师需要授课~~~~");
    }

    @Override
    public void run() {

    }
}

// 员工（老师，班主任 ） 每个员工都要工作，但是工作内容不一样。
abstract class Employee{
    public abstract void work();
    public abstract void run();
}
```

> * 一个类继承了抽象类，必须重写完抽象类的全部抽象方法，否则这个类必须定义成抽象类。
> * 因为拥有抽象方法的类必须定义成抽象类。

### 抽象类的特征

抽象类的特征是：**有得有失**

* 抽象类得到了拥有对象的能力
* 抽象类失去了创建对象的能力，即抽象类不能创建对象

> **抽象类是否有构造器，是否可以创建对象**，为什么?
>
> 抽象类作为类一定**有构造器**，而且必须有构造器。**提供给子类继承后调用父类构造器**使用的。
>
> 抽象类虽然有构造器，但是抽象类**绝对不能创建对象**。抽象类中可能存在抽象方法，**抽象方法不能执行**。抽象在学术上本身意味着不能实例化。

### 抽象类的意义

抽象类存在的意义有两点：

* **被继承**：抽象类就是为了被子类继承，否则抽象类将毫无意义
* 抽象类体现的**模板思想**：部分实现，部分抽象，可以使用抽象类设计一个模板模式

### 抽象类设计模板模式

**设计模式**：就是前人或者软件行业在生产实战中发现的优秀软件设计架构和思想。后来者可以直接用这些架构或者思想就可以设计出优秀的软件，提高开发效率，提高软件可扩展性和可维护性。

模板设计模式就是一种经典的设计模式思想

**模板设计模型的作用**：优化代码架构，提高代码的复用性，相同功能的重复代码无需重复书写。可以做到部分实现，部分抽象，抽象的东西交给使用模板的人重写实现

```java
class Teacher extends Template{
    @Override
    public String writeMain() {
        return "\t\t我爸就是好，有多好，做他儿子才能懂~~~";
    }
}

class Student extends Template{
    @Override
    public String writeMain() {
        return "\t\t我爸爸很牛，我爸爸是马云，就是爽，很有钱~~~~";
    }
}
// 1.写一个模板类：代表了作文模板。
abstract class Template{
    private String title = "\t\t\t\t\t\t《我的爸爸》";
    private String one = "\t\t我的爸爸很牛逼，到底有多牛呢，请看如下说明：";
    private String last = "\t\t以上就是我的爸爸，简直太好了，下辈子还要做他儿子！";

    // 2.提供一个写作文方法
    public void write(){
        System.out.println(title);
        System.out.println(one);
        // 正文：正文部分模板是不知道怎么写的！应该把正文部分定义成抽象方法
        // 交给使用模板的子类重写！
        System.out.println(writeMain());
        System.out.println(last);
    }

    // 正文部分定义成抽象方法，交给子类重写！！
    public abstract String writeMain();
}
```

### 抽象类的注意事项

1. 抽象类不能创建对象，如果创建，编译无法通过而报错。只能创建其非抽象子类的对象。
    理解：假设创建了抽象类的对象，调用抽象的方法，而抽象方法没有具体的方法体，没有意义。

2. 抽象类一定有而且是必须有构造器，是供子类创建对象时，初始化父类成员使用的。
    理解：子类的构造器中，有默认的super()，需要访问父类构造器。

3. 抽象类中，不一定包含抽象方法，但是有抽象方法的类必定是抽象类。

4. 抽象类的子类，必须重写抽象父类中所有的抽象方法，否则子类也必须定义成抽象类。

5. 抽象类存在的意义是为了被子类继承，抽象类体现的是模板思想。
   理解：抽象类中已经实现的是模板中确定的成员，
   抽象类不确定如何实现的定义成抽象方法，交给具体的子类去实现。

## 接口

### 接口的概述

接口体现的是规范思想，实现接口的子类必须重写完接口的全部抽象方法

接口是更加彻底的抽象，在JDK 1.8之前接口中只能是抽象方法和常量

定义格式

```java
修饰符 interface 接口名称{
	// 在JDK 1.8 之前接口中只能是抽象方法和常量
}
```

成分研究（JDK 1.8之前）

* 接口中抽象方法默认加上`public abstract`修饰，可以省略不写

* 常量是指有`public static final`修饰的成员变量，**有且仅能被复制一次**，值不能改变

  常量名称规范要求全部大写，多个单词下划线连接

  常量修饰`public static final`可以省略不写，默认会加上

```java
public interface InterfaceDemo {
     // 2.常量
     // 只有一份，在执行的过程中其值必须有，但是不能改变！
     // 常量是public static final修饰
     // 常量的名称建议字母全部大写，多个单词用“_”连接
     // 在接口中常量可以省略public static final不写，默认会加上该三个修饰符！
     //public static final String SCHOOL_NAME = "黑马";
     String SCHOOL_NAME = "黑马";


     // 1.抽象方法
     // public abstract void run();
     // 接口中的抽象方法默认会加上public abstract修饰,所以可以省略不写。
     void run();
     void work();
}
```

### 接口的基本实现

子类和父类是继承，实现类和接口是实现关系。接口是用来被类实现的，实现接口的类是实现类

子类–>继承–>父类

实现类–>实现–>接口

类实现接口的格式：

```java
修饰符 class 实现类名称 implements 接口1,接口2,接口3,....{

}
```

例如

```java
// 实现类 实现 SportMan接口
// 一个类实现接口必须重写完接口中全部抽象方法，否则这个类必须定义成抽象类！！
class PingPongMan implements SportMan{
    private String name;
    public PingPongMan(String name){
        this.name = name;
    }
    @Override
    public void run() {
        System.out.println(name+"必须天天运动。正在🏃训练~~~‍");
    }

    @Override
    public void win() {
        System.out.println(name+"参加比赛中~~~‍");
    }
}

// 定义一个接口：表示运动员的规范
interface SportMan{
    void run(); // 跑步
    void win(); // 比赛得奖
}
```

> * 接口的使命就是要求实现接口的类必须有`run()`和`win()`方法
> * 接口可以多实现
> * 一个类实现接口必须重写完接口中全部抽象方法，否则这个类必须定义成抽象类

### 接口的多实现

* 类与类是单继承
* 类和接口是多实现

一个类如果实现了多个接口，必须重写完全部接口中的全部抽象方法，否则这个类必须定义为抽象类

```java
class PingPongMan implements SportMan , Law{

    @Override
    public void rule() {

    }

    @Override
    public void run() {

    }

    @Override
    public void win() {

    }
}

interface Law{
    void rule();
    void run();
}

interface SportMan{
    void run();
    void win();
}
```

### 接口与接口的多继承

* 类与类是单继承关系：一个类只能继承一个直接父类
* 类与接口是多继承关系：一个类可以同时实现多个接口
* 接口与接口是多继承关系：一个接口可以同时继承多个接口

接口与接口的多继承，用一个接口合并多个接口

```java
class PingPongMan implements SportMan{

    @Override
    public void eat() {

    }

    @Override
    public void rule() {

    }

    @Override
    public void run() {

    }

    @Override
    public void goAbroad() {

    }
}

interface Food{
    void eat();
}

interface Law{
    void rule();
}

// 接口与接口的多继承！
interface SportMan extends Law , Food {
    void run();
    void goAbroad();
}
```

### JDK1.8之后接口新增的方法

JDK1.8开始之后接口新增的三个方法，了解即可

* **默认方法**	就是之前写的实例方法
  * 必须用`default`修饰
  * 默认会加`public`修饰
  * 只能用接口的实现类的对象来调用
* **静态方法**
  * 可以直接加`static`修饰
  * 默认会加`public`修饰
  * 接口的静态方法只能用接口的类名称调用
* **私有方法**    从JDK 1.9开始才支持的
  * 其实就是私有的实例方法，必须加`private`修饰
  * 只能在**本接口**被其他的默认方法或者私有方法访问

```java
public class InterfaceDemo {
    public static void main(String[] args) {
        // 1.默认方法调用：必须用接口的实现类对象调用。
        PingPongMan zjk = new PingPongMan();
        zjk.run();
        zjk.work();

        // 2.接口的静态方法必须用接口的类名本身来调用。
        InterfaceJDK8.inAddr();
    }
}

class PingPongMan implements InterfaceJDK8{
    @Override
    public void work() {
        System.out.println("工作中。。。");
    }
}

interface InterfaceJDK8{
    // 之前的抽象方法！！
    void work();

    // a.默认方法（就是之前写的普通实例方法）
    // 必须用接口的实现类的对象来调用。
    default void run(){
        go();
        System.out.println("开始跑步🏃‍~~~~");
    }

    // b.静态方法
    // 注意：接口的静态方法必须用接口的类名本身来调用
    static void inAddr(){
        System.out.println("我们在吉山区~~~~");
    }

    // c.私有方法（就是私有的实例方法）: JDK 1.9才开始有的。
    //  -- 只能在本接口中被其他的默认方法或者私有方法访问。
    private void go(){
        System.out.println("开始。。");
    }
}
```

### 接口的注意事项

* 如果实现了多个接口，多个接口存在同名的**静态方法**并不会从冲突，原因是只能通过各自接口方法访问各自静态方法
* 当一个类，即继承一个父类，又实现若干个接口时，父类的成员方法与接口中的默认方法重名时，之类**就近**选择执行父类的成员方法
* 当一个类实现多个接口时，多个接口存在同名的默认方法，实现类必须重写这个方法

## 代码块

代码块按照有无`static`修饰可以分为：静态代码块、实例代码块

### 静态代码块

静态代码块：必须用`static`修饰，必须放在类下，与类一起优先加载执行

静态代码块可以用于执行类的方法之前进行静态资源的初始化操作

```java
public class CodeDemo01 {
    public static String schoolName ;
    public static ArrayList<String> lists = new ArrayList<>();

    // 静态代码块,属于类，与类一起加载一次!
    static {
        System.out.println("静态代码块被触发执行~~~~~~~");
        // 在静态代码块中进行静态资源的初始化操作
        schoolName = "黑马";
        lists.add("3");
        lists.add("4");
        lists.add("5");
    }

    public static void main(String[] args) {
        System.out.println(schoolName);
        System.out.println(lists);
    }
}
```

### 实例代码块

* 实例代码块直接用`{}`括起来，无需`static`修饰
* 会和类的对象一起加载，每次创建对象的时候，实例代码块会被加载且自动执行一次
* 实例代码块的代码在底层实际上是提取到每个构造器中去执行的，**实例代码块属于对象**
* 实例代码块可以在创建对象之前进行实例资源的初始化操作

```java
public class CodeDemo02 {
    private String name;
    private ArrayList<String> lists = new ArrayList<>();
    // 实例代码块！属于对象！与对象一起加载!
    {
        name = "小手";
        lists.add("东");
        lists.add("南");
        lists.add("西");
        lists.add("北");
        System.out.println("实例代码块被触发执行一次~~~~~~~~");
    }

    public CodeDemo02(){

    }
    public CodeDemo02(String name){

    }

    public static void main(String[] args) {
        CodeDemo02 c = new CodeDemo02();
        System.out.println(c.name);
        System.out.println(c.lists);
        new CodeDemo02();
        new CodeDemo02();
    }
}
```

## final关键词

`final`可以用于修饰类、方法、变量

* `final`修饰类：类不能被继承了
* `final`修饰方法：方法不能被重写
* `final`修饰变量：变量有且仅能被赋值一次
  * 局部变量-只能赋值一次，不能在更改
  * 实例成员变量
    * 显示初始化，在定义成员变量的时候立马赋值
    * 实例代码块中赋值一次
    * 构造器初始化，在构造器中赋值一次

`final`和`abstract`的关系

互斥关系，不能同时修饰类或者同时修饰方法

**常量**：有`public static final`修饰，名称字母全部大写，多个单词用下划线连接

## 单例设计模式

**单例**的意思是一个类永远只存在一个对象，不能创建多个对象

* 开发中有很多类的对象我们只需要一个对象，例如虚拟机，任务管理器对象
* 对象越多越占内存，有时候只需要一个对象就可以实现业务，单例可以节约内存，提高性能

### 饿汉单例设计模式

通过类获取单例对象的时候，对象已经提前准备做好了

设计步骤：

* 定义一个类，把**构造器私有**
* 定义一个静态变量存储一个对象
* 提供一个返回单例对象的方法

```java
// 饿汉单例设计模式
class Singleton01{
    //  b.定义一个静态变量存储一个对象( 在用类获取对象的时候，对象已经提前为你创建好了。)
    private static final Singleton01 INSTANCE = new Singleton01();
    //  a.定义一个类，把构造器私有。
    private Singleton01(){
    }
    // c.提供一个返回单例对象的方法。
    public static Singleton01 getInstance(){
        return INSTANCE;
    }
}
```

### 懒汉单例设计模式

通过类获取单例对象的时候发现没有对象才会去创建一个对象

设计步骤：

* 定义一个类，把**构造器私有**
* 定义一个静态成员变量用于存储一个对象
* 提供一个返回单例对象的方法，判断对象不存在才创建一次，存在直接返回

```java
// 懒汉单例设计模式
class Singleton02{
    //  b.定义一个静态变量存储一个对象(这里不能创建对象，需要的时候才创建，这里只是一个变量用于存储对象！)
    public static Singleton02  instance ;

    //   a.定义一个类，把构造器私有。
    private Singleton02(){

    }
    //  c.提供一个返回单例对象的方法。
    public static Singleton02 getInstance(){
        if(instance == null){
            // 第一次来拿单例对象！需要创建一次对象，以后直接返回！！
            instance = new Singleton02();
        }
        return instance;
    }
}
```

## 枚举

枚举类的作用：是为了做信息的标志和信息分类

### 枚举类基本语法

```java
修饰符 enum 枚举名称{
	// 第一行罗列的必须的枚举类的对象名称
}
```

例如

```java
enum Sex{
    BOY , GIRL;
}

// 枚举
enum Season {
    SPRING , SUMMER , AUTUMN , WINTER;
}
```

枚举类反编译以后的源代码

```Java
public final class Season extends java.lang.Enum<Season> {
    public static final Season SPRING = new Season();
    public static final Season SUMMER = new Season();
    public static final Season AUTUMN = new Season();
    public static final Season WINTER = new Season();

    public static Season[] values();
    public static Season valueOf(java.lang.String);
}
```

### 枚举类的特点

* 枚举类是`final`修饰的，不能被继承
* 枚举类默认继承了枚举类型`java.lang.Enum`
* 枚举类的第一行罗列的是枚举类的对象，而且是用常量存储的
* 所以枚举类的第一行写的都是常量名称，默认存储了枚举对象
* 枚举类的构造器默认是私有的
* 枚举类相当于是多例设计模式

> `Java`建议做信息标志和信息分类应该使用**枚举**实现，比较优雅，可以实现可读性，而且入参受限制，不能乱输入

# day3-多态、包、权限修饰符、内部类、object类、Date类

## 多态==重点==

面向对象的三大特征：封装、继承、多态

多态的形式：

```java
父类类型 变量名 = new 子类/实现类构造器;
变量名.方法名();
```

```java
public class PolymorphicDemo {
    public static void main(String[] args) {
        //  父类类型 对象名称 = new 子类构造器;
        Animal dlam = new Cat();
        dlam.run(); // 对于方法的调用：编译看左边，运行看右边。
        System.out.println(dlam.name); // 对于变量的调用：编译看左边，运行看左边。

        Animal taiDi = new Dog();
        taiDi.run(); // 对于方法的调用：编译看左边，运行看右边。
        System.out.println(taiDi.name); // 对于变量的调用：编译看左边，运行看左边。
    }
}

class Dog extends Animal{
    public String name = "🐶名称Dog";
    @Override
    public void run(){
        System.out.println("🐕跑的贼快~~~~！");
    }
}

class Cat extends Animal{
    public String name = "🐱名称Cat";
    @Override
    public void run(){
        System.out.println("🐱跑的飞快~~~~！");
    }
}

class Animal{
    public String name = "动物名称Animal";
    public void run(){
        System.out.println("动物跑！");
    }
}
```

**多态的概念：**同一个类型的对象，执行同一个行为，在不同的状态下会表现出不同的行为特征

**多态的识别技巧：**

* 对于方法的调用：编译看左边，运行看右边
* 对于变量的调用：编译看左边，运行看左边

**多态的使用前提**

* 必须存在继承或者实现关系
* 必须存在父类类型的变量引用子类类型的对象
* 需要存在方法重写













