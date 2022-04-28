package com.itheima._09继承后_成员变量的访问特点;

/**
    目标：继承后-成员变量的访问特点。

    就近原则：
        子类有找子类，子类没有找父类，父类没有就报错。

    小结：
        this代表了当前对象的引用，可以用于访问当前子类对象的成员变量。
        super代表了父类对象的引用，可以用于访问父类中的成员变量。
 */
public class TestDemo {
    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.show();
    }
}

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
        // System.out.println(name2); // 报错了！
    }
}

