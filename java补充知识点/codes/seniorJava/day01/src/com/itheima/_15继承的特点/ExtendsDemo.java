package com.itheima._15继承的特点;

/**
    目标：继承的特点；

    1.单继承：一个类只能继承一个直接父类。
        为什么Java是单继承的？
            答:反证法，假如Java可以多继承，请看如下代码：
            class A{
                public void test(){
                    System.out.println("A");
                }
            }
            class B{
                public void test(){
                    System.out.println("B");
                 }
            }
            class C extends A , B {
                public static void main(String[] args){
                    C c = new C();
                    c.test(); // 出现了类的二义性！所以Java不能多继承！！
                }
            }

    2.多层继承：一个类可以间接继承多个父类。（家谱）
    3.一个类可以有多个子类。
    4.一个类要么默认继承了Object类，要么间接继承了Object类，Object类是Java中的祖宗类！！

 */
public class ExtendsDemo {
}

class A {}
class B extends A{}
class C extends B{}
class D extends B{}