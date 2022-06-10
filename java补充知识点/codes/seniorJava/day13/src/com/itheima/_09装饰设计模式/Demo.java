package com.itheima._09装饰设计模式;

/**
    目标：装饰模式。

    装饰模式指的是在不改变原类, 动态地扩展一个类的功能。
    思想：是创建一个新类，包装原始类，从而在新类中提升原来类的功能！！
    小结：
         装饰模式可以在不改变原类的基础上对类中的方法进行扩展增强,实现原则为:
         1.定义父类。
         2.定义原始类，继承父类，定义功能。
         3.定义装饰类，继承父类，包装原始类，增强功能！！
 */
public class Demo {
    public static void main(String[] args) {
        InputStream is = new BufferedInputStrem(new FileInputStream());
        is.read();
        is.close();
    }
}
