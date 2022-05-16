package com.itheima._01抽象类的概述;

/**
    目标：抽象类的入门概述。

    引入：
        父类知道子类一定要完成某个功能，但是每个子类实现的情况都不一样
        而且子类都会用自己的功能了，父类的该功能就可以定义成抽象的方法。
        拥有抽象方法的类必须定义成抽象类。
    什么是抽象方法?
        没有方法体，只有方法签名，必须用abstract修饰的方法就是抽象方法。
    什么是抽象类？
        拥有抽象方法的类必须定义成抽象类。
        抽象类必须用abstract关键字修饰。
    小结：
        抽象方法：没有方法体，只有方法签名，必须用abstract修饰的方法就是抽象方法。
        抽象类：拥有抽象方法的类必须定义成抽象类，必须用abstract修饰。
 */
public class AbstractDemo {
    public static void main(String[] args) {
        Wolf qpl = new Wolf();
        qpl.run();
    }
}

class Wolf extends Animal{
    @Override
    public void run(){
        System.out.println("🐺跑的贼贼溜~~~");
    }
}

// 抽象类：拥有了抽象方法的类必须定义成抽象类。抽象类必须加上abstract修饰。
abstract class Animal{
    // 抽象方法：没有方法体，只有方法签名，必须加上abstract修饰。
    public abstract void run();
}