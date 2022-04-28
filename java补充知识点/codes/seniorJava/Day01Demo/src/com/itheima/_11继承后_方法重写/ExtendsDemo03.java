package com.itheima._11继承后_方法重写;

/**
    目标：静态方法和私有方法是否可以被重写（拓展语法）

    可以吗?  都不可以.
 */
public class ExtendsDemo03 {
}

class Mac extends Computer{
    //@Override
    public void go(){
    }

    // @Override
    public static void test(){
    }
}

class Computer{
    public static void test(){
    }

    private void go(){

    }
}
