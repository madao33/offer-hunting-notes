package com.itheima._06异常_运行时异常的处理机制;

/**
    目标：运行时异常的处理机制。

    运行时异常在编译阶段是不会报错，在运行阶段才会出错。
    运行时异常在编译阶段不处理也不会报错，但是运行时如果出错了程序还是会死亡
    所以运行时异常也建议要处理。

    运行时异常是自动往外抛出的，不需要我们手工抛出。

    运行时异常的处理规范：直接在最外层捕获处理即可，底层会自动抛出！！

    小结：
         运行时异常编译阶段不报错，可以处理也可以不处理，建议处理！！
         运行时异常可以自动抛出，不需要我们手工抛出。
         运行时异常的处理规范：直接在最外层捕获处理即可，底层会自动抛出！！
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        System.out.println("程序开始。。。。");
        try{
            chu(10 , 0);
            System.out.println("操作成功！");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("操作失败！");
        }
        System.out.println("程序结束。。。。");
    }

    public static void chu(int a , int b)  {
        System.out.println( a / b );
    }
}
