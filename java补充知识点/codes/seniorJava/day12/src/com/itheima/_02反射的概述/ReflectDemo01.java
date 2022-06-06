package com.itheima._02反射的概述;
/**
    目标：反射的概念。

    反射，注解，代理，泛型是Java的高级技术，是以后框架的底层原理必须使用到的技术。

    反射：是Java独有的技术。是Java技术显著的特点。

    反射是指对于任何一个类，在"运行的时候"都可以直接得到这个类全部成分。
        在运行时,可以直接得到这个类的构造器对象。（Constructor）
        在运行时,可以直接得到这个类的成员变量对象。（Field）
        在运行时,可以直接得到这个类的成员方法对象。（Method）

    反射的核心思想和关键就是得到：编译以后的class文件对象。

    反射提供了一个Class类型，就是可以得到编译以后的class类对象。
        HelloWorld.java -> javac -> HelloWorld.class

        Class c = HelloWorld.class;


    注意：反射是工作在运行时的技术，因为只有运行之后才会有class类对象。

    小结：
        反射的核心思想和关键就是得到：编译以后的class文件对象。
        反射是在运行时获取类的字节码文件对象：然后可以解析类中的全部成分。
 */
public class ReflectDemo01 {
}
