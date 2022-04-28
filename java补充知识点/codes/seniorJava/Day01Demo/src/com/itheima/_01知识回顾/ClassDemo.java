package com.itheima._01知识回顾;

/**
    目标：之前内容的快速回顾。

    面向对象思想概述：
        Java是一种面向对象的高级编程语言。
        面向对象：是用代码去高度模拟现实世界的事物，从而让软件为任务处理业务，为人类服务。
        高级语言：代码看起来很像人类的自然语言。

    面向对象最重要的两个概念：类和对象。
        类是相同事物共同特征的描述。类只是学术上的一个概念并非真实存在的，只能描述一类事物。
        对象：是真实存在的实例。  实例==对象。
        结论：有了类和对象就可以描述万千世界所有的事物。
             必须先有类才能有对象。

    定义类：
        格式：修饰符 class 类名{

        }
        注意：1.类名的首字母建议大写。满足驼峰模式。 StudentNameCode
             2.一个Java代码文件中可以定义多个类。但是按照规范还是建议一个Java文件定义一个类。
             3.一个Java代码文件中，只能有一个类是用public修饰的，
               而且public修饰的类名必须成为当前Java代码的文件名称。

    类中的成分:有且仅有五大成分（五大金刚）
        修饰符 class 类名{
            // 1.成员变量(Field):  描述类或者对象的属性信息的。
            // 2.成员方法(Method): 描述类或者对象的行为信息的。
            // 3.构造器(Constructor): 初始化一个对象返回。
            // 4.代码块(后面学习的)
            // 5.内部类(后面学习的)
        }
        类中有且仅有这五种成分，否则代码报错！

    构造器：
        格式：修饰符 类名(形参列表){

             }
        作用：初始化类的一个对象返回。
        构造器的分类：无参数构造器，有参数构造器。
        构造器的注意点：一个类默认自带一个无参数构造器，但是如果写了有参数构造器那么
            默认的无参数构造器就消失了，此时如果还需要用无参数构造器就需要自己从新写一个。
        构造器初始化对象的格式：
            类名 对象名称 = new 构造器;
            Student s = new Student();
        无参数构造器的作用：初始化一个类的对象（使用对象的默认值初始化）返回。
        有参数构造器的作用：初始化一个类的对象（可以在初始化对象的时候为对象赋值）返回。

 */
public class ClassDemo {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("铁扇公主");
        System.out.println(s1.getName()); // 铁扇公主

        Student s2 = new Student("铁扇公主");
        System.out.println(s2.getName()); //  铁扇公主
    }
}

class Student{
    private String name ;

    public Student(){

    }
    public Student(String name){
        this.name = name ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Animal{

}