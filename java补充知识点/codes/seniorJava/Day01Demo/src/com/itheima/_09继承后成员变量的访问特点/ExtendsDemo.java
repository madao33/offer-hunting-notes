package com.itheima._09继承后成员变量的访问特点;

/**
    目标：继承后成员变量的访问特点。

    继承后成员变量的访问特点:就近原则
        -- 子类有找子类，子类没有找父类，父类没有就报错！

    如果一定要申明访问父类的成员变量可以使用：super.父类成员变量。
        -- super指父类引用。
    小结：
        子类访问成员变量的原则：就近原则。
        如果一定要访问父类的成员变量可以使用super关键字。
 */
public class ExtendsDemo {
    public static void main(String[] args) {
        Wolf qpl = new Wolf();
        qpl.showName();

    }
}

class Wolf extends Animal{
    private String name = "子类狼";

    public void showName(){
        String name = "局部名称";
        System.out.println(name); // 局部name
        System.out.println(this.name); // 子类对象的name
        System.out.println(name1); // 父类的
        System.out.println(super.name); // 父类的
        //System.out.println(name2); // 报错。子类父类都没有
    }
}

class Animal{
    public String name = "父类动物名称";
    public String name1 = "父类名称1";
}


