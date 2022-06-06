package com.itheima._09注解的概念;
/**
     目标：注解的概念。

     注解：
            用在类上，方法上，成员变量，构造器，...上对成分进行编译约束，标记等操作的。
            注解是JDK1.5的新特性。
            注解相当一种标记，是类的组成部分，可以给类携带一些额外的信息。
            注解是给编译器或JVM看的，编译器或JVM可以根据注解来完成对应的功能。

     注解作用：
            1.标记。
            2.方法重写约束 @Override
            3.函数式接口约束。 @FunctionalInterface.
            4.现今最牛逼的框架技术多半都是在使用注解和反射。都是属于框架的底层基础技术。

     我们之前用的注解都是别人写好的，今天我们自己来定义一下注解。

 */
public class AnnotationDemo01 {

}

@FunctionalInterface
interface A{
    void test();
}


