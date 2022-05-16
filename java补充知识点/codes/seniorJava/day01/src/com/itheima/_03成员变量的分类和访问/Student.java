package com.itheima._03成员变量的分类和访问;

/**
    目标：成员变量的分类和访问。

    按照有无static修饰成员变量分为：
        （1）静态成员变量：有static修饰，属于类本身与类一起加载一次，直接用类名访问即可。
        （2）实例成员变量：无static修饰，属于类的每个对象的，必须先创建对象，再用对象来访问。

    成员变量的访问语法：
         静态成员变量访问：
              类名.静态成员变量。
              对象.静态成员变量。（不推荐）
         实例成员变量的访问：
              对象.实例成员变量。

    小结：
        静态成员变量有static修饰，属于类本身，与类加载一次，因为只有一份所以可以被类和类的对象共享访问。
            注意：不建议用对象访问静态成员变量，静态成员变量直接用类名访问即可！！！

        实例成员变量，无static修饰，属于类的对象的，必须先创建对象，然后用对象来访问！
 */
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





