package com.itheima._08异常的注意事项;
/**

     目标：异常的语法注意：（了解）
         - 运行时异常被抛出可以不处理。可以自动抛出,编译时异常必须处理.按照规范都应该处理!
         - 重写方法申明抛出的异常，应该与父类被重写方法申明抛出的异常一样或者范围更小
         - 方法默认都可以自动抛出运行时异常！  throws RuntimeException可以省略不写!!
         - 当多异常处理时，捕获处理，前边的异常类不能是后边异常类的父类。
         - 在try/catch后可以追加finally代码块，其中的代码一定会被执行，通常用于资源回收操作。
*/
public class ExceptionDemo {
}

class Fu{
    public void run() throws Exception{

    }
}

class Zi extends Fu{
    @Override
    public void run() throws Exception{

    }
}