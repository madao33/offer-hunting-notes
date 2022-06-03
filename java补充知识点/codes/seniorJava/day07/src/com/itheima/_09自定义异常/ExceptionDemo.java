package com.itheima._09自定义异常;
/**
    目标:自定义异常(了解)

    引入:Java已经为开发中可能出现的异常都设计了一个类来代表.
        但是实际开发中,异常可能有无数种情况,Java无法为
        这个世界上所有的异常都定义一个代表类。
        假如一个企业如果想为自己认为的某种业务问题定义成一个异常
        就需要自己来自定义异常类.

    需求：认为年龄小于0岁，大于200岁就是一个异常。

    自定义异常:
        自定义编译时异常.
            a.定义一个异常类继承Exception.
            b.重写构造器。
            c.在出现异常的地方用throw new 自定义对象抛出!
            编译时异常是编译阶段就报错，提醒更加强烈，一定需要处理！！

        自定义运行时异常.
            a.定义一个异常类继承RuntimeException.
            b.重写构造器。
            c.在出现异常的地方用throw new 自定义对象抛出!
            提醒不强烈，编译阶段不报错！！运行时才可能出现！！
    小结：
        自定义异常是程序员自己定义的异常
        继承Exception/RuntimeException，重写构造器。
        在出现异常的地方用throw new 自定义异常对象抛出!
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            checkAge(101);
        } catch (ItheimaAgeIllegalException e) {
            e.printStackTrace();
        }
    }

    public static void checkAge(int age) throws ItheimaAgeIllegalException {
        if(age < 0 || age > 200){
            // 出现异常了！
            // throws:用在方法上，用于抛出方法中的异常。
            // throw:用在出现异常的地方，用于创建异常对象且立即从此处抛出！
            throw new ItheimaAgeIllegalException("/ age is illegal!");
            //throw new ItheimaAgeIllegalRuntimeException("/ age is illegal!");
        }else{
            System.out.println("年龄是："+age);
        }
    }
}
