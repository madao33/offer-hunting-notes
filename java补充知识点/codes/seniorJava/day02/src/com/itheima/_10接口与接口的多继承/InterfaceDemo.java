package com.itheima._10接口与接口的多继承;

/**
    目标：接口与接口的多继承。

    引入：
        类与类是单继承关系：一个类只能继承一个直接父类。
        类与接口是多实现关系：一个类可以实现多个接口。
        接口与接口是多继承关系：一个接口可以继承多个接口。
 */
public class InterfaceDemo {
}

class PingPongMan implements SportMan{

    @Override
    public void eat() {

    }

    @Override
    public void rule() {

    }

    @Override
    public void run() {

    }

    @Override
    public void goAbroad() {

    }
}

interface Food{
    void eat();
}

interface Law{
    void rule();
}

// 接口与接口的多继承！
interface SportMan extends Law , Food {
    void run();
    void goAbroad();
}