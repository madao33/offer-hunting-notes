package com.itheima._01知识回顾;

/**
    目标：this关键字的使用总结。

    this关键字代表了当前对象的引用。
    this可以出现在方法，构造器中。
    this出现在方法中：哪个对象调用这个方法this就代表谁。
    this可以出现在构造器中：代表构造器正在初始化的那个对象。
    this可以区分变量是访问的成员变量还是局部变量。
 */
public class ThisDemo03 {
    public static void main(String[] args) {
        Pig pig = new Pig();
        pig.setName("佩奇");
        pig.setAge(1);
        System.out.println(pig.getName());
        System.out.println(pig.getAge());

        Pig qiaoZhi = new Pig("乔治",2);
        System.out.println(qiaoZhi.getName());
        System.out.println(qiaoZhi.getAge());
    }
}

class Pig{
    private String name ;
    private int age ;

    public Pig(){

    }

    public Pig(String name, int age) {
        // this在构造器代表构造器正在初始化的那个对象。
        this.name = name; // qiaoZhi.name = 乔治
        this.age = age; // qiaoZhi.age = 2
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // 哪个对象调用方法，this就代表谁。
        this.name = name; // pig.name = 佩奇
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}