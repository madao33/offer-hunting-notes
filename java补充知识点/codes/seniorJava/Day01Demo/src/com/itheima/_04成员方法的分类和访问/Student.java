package com.itheima._04成员方法的分类和访问;

/**
    目标：成员方法的分类和访问。

    成员方法按照有无static修饰可以分为：
        （1）静态方法：有static修饰，属于类，直接用类名访问即可。
        （2）实例方法：无static修饰，属于对象的，必须用对象来访问。
    成员方法的访问语法：
        静态方法的访问格式：
            类名.静态方法
            对象.静态方法（不推荐）
        实例方法的访问格式：
            对象.实例方法
    小结：
        静态方法属于类，有static修饰，直接用类名访问即可。
        实例方法属于对象，无static修饰，必须先创建对象，然后用对象来访问。

        静态方法也可以被对象共享访问，但是不推荐，因为静态方法直接用类名访问即可。

 */
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
