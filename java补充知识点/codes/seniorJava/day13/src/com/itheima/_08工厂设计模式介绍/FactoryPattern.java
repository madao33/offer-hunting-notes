package com.itheima._08工厂设计模式介绍;
// 工厂设计模式
public class FactoryPattern {
    // 生产对象的方法：工厂方法
    public static Animal createAniaml(){
//        Dog dog = new Dog();
//        return dog;
        return new Cat();
    }

}
