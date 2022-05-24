package com.itheima._09内部类_局部内部类;

/**
    目标：局部内部类。（几乎不用）

    定义在方法中，在构造器中，代码块中，for循环中定义的内部类
    就是局部内部类。

    局部内部类中的成分特点：
        只能定义实例成员，不能定义静态成员
        可以定义常量的。
    小结：
        局部内部类没啥用。

 */
public class InnerClass {

    static {
        abstract class A{

        }
    }

    public static void main(String[] args) {
        class A{
            private String name;

            public void test(){
            }
            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        A a = new A();
        a.test();
    }

    public static void test(){
       class Animal{

       }

       class Cat extends Animal{

       }
    }
}
