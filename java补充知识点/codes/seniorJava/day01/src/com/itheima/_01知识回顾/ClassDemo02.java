package com.itheima._01知识回顾;

/**
    目标：封装的复习回顾。

    面向对象的三大特征：封装，继承，多态。

    封装的哲学思维：合理隐藏，合理暴露。
    封装最初的目的：提高代码的安全性和复用性,组件化。
    封装的步骤：
        1.成员变量应该私有。用private修饰，只能在本类中直接访问。
        2.提供成套的getter和setter方法暴露成员变量的取值和赋值。

    封装实际上已经成为了Java的代码规范，即使代码毫无意义我们还是要这样写。

 */
public class ClassDemo02 {
    public static void main(String[] args) {
        Person p = new Person();
        p.setAge(10);
        System.out.println(p.getAge());
    }
}

class Person{
    private int age ;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
