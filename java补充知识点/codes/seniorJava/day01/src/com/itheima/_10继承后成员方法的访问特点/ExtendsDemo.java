package com.itheima._10继承后成员方法的访问特点;

/**
    目标：继承后成员方法的特点。

    继承后成员方法的特点：就近原则。
        子类有找子类，子类没有找父类，父类没有就报错！
 */
public class ExtendsDemo {
    public static void main(String[] args) {
        Dog taiDi = new Dog();
        taiDi.run(); // 子类的。
        taiDi.eat(); // 父类的。
        //taiDi.go(); // 报错
    }
}

class Animal{
    public void run(){
        System.out.println("父类动物可以跑~~~");
    }
    public void eat(){
        System.out.println("动物吃东西~~");
    }
}

class Dog extends Animal{
    public void run(){
        System.out.println("狗跑的贼溜~~~");
    }
}