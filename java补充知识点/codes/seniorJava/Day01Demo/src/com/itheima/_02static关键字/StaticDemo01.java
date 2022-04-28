package com.itheima._02static关键字;

/**
    目标：static关键字的概述。(重点)

    引入：
        我们之前定义了很多成员变量（name , age , sex）
        其实我们只写了一份，但是发现每个对象都可以用，就说明
        Java中这些成员变量或者方法是存在所属性的。
        有些是属于对象的，有些是属于类本身的。

    Java是通过成员变量是否有static修饰来区分是类的还是属于对象的。

    static == 静态 == 修饰的成员（方法和成员变量）属于类本身的。

    按照有无static修饰，成员变量和方法可以分为：
        成员变量：
            （1）静态成员变量（类变量）：
                     有static修饰的成员变量称为静态成员变量也叫类变量，属于类本身的，
                     直接用类名访问即可。

            （2）实例成员变量
                    无static修饰的成员变量称为实例成员变量，属于类的每个对象的，
                    必须用类的对象来访问。

        成员方法：
            （1）静态方法
                    有static修饰的成员方法称为静态方法也叫类方法，属于类本身的，
                    直接用类名访问即可。

            （2）实例方法
                    无static修饰的成员方法称为实例方法，
                    属于类的每个对象的，必须用类的对象来访问。

    小结：
        成员变量有2种
             -- 有static修饰的属于类叫静态成员变量，与类一起加载一次，直接用类名调用即可。
             -- 无static修饰的属于类的每个对象的叫实例成员变量，
                与类的对象一起加载，对象有多少个，实例成员变量就加载多少份。必须用类的对象调用。

        成员方法有2种：
             -- 有static修饰的属于类叫静态方法，直接用类名调用即可。
             -- 无static修饰的属于类的每个对象的叫实例方法，必须用类的对象调用。
 */
public class StaticDemo01 {
    
}
