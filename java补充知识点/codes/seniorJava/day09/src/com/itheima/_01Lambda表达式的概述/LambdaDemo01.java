package com.itheima._01Lambda表达式的概述;
/**
     目标：Lambda表达式的概述。

     什么是Lambda表达式？
         Lambda表达式是JDK1.8开始之后的新技术，是一种代码的新语法。
         是一种特殊写法，
         作用：“核心目的是为了简化匿名内部类的代码写法”。

     Lambda表达式的格式:
        (匿名内部类被重写方法的形参列表) -> {
           被重写方法的方法体代码。
        }

        -> 就是一个新语法，没有实际含义，但是不能省略！

     Lambda表达式的使用前提：
         （1）Lambda表达式并不能简化所有匿名内部类的写法。
         （2）Lambda表达式只能简化接口中只有一个抽象方法的匿名内部类形式。

     Lambda表达式只能简化函数式接口的匿名内部类写法：
         a.首先必须是接口。
         b.接口中只能有一个抽象方法。
     小结：
        Lambda表达式只能简化接口中只有一个抽象方法的匿名内部类写法。
        接口中只有一个抽象方法的接口称为函数式接口。
        Lambda只能简化函数式接口的匿名内部类写法。
 */
public class LambdaDemo01 {
    public static void main(String[] args) {
//        Thread t = new Thread(){
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName()+":执行~~~");
//            }
//        };
//        Thread t = () -> {
//                System.out.println(Thread.currentThread().getName()+":执行~~~");
//        };
//        t.start();
    }
}