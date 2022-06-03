package com.itheima._02异常_常见的运行时异常;
/**
    拓展: 常见的运行时异常。（面试题）
         运行时异常的概念:
             继承自RuntimeException的异常或者其子类，
             编译阶段是不会出错的，它是在运行时阶段可能出现的错误，
             运行时异常编译阶段可以处理也可以不处理,代码编译都能通过！！

             1.数组索引越界异常: ArrayIndexOutOfBoundsException。
             2.空指针异常 : NullPointerException。
               直接输出没有问题。但是调用空指针的变量的功能就会报错！！
             3.类型转换异常：ClassCastException。
             4.迭代器遍历没有此元素异常：NoSuchElementException。
             5.数学操作异常：ArithmeticException。
             6.数字转换异常： NumberFormatException。

    小结：
        运行时异常继承了RuntimeException ,编译阶段不报错，运行时才可能会出现错误!
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        System.out.println("程序开始。。。。。。");
        /** 1.数组索引越界异常: ArrayIndexOutOfBoundsException。*/
        int[] arrs = {10 ,20 ,30};
        System.out.println(arrs[2]);
        // System.out.println(arrs[3]); // 此处出现了数组索引越界异常。代码在此处直接执行死亡！

        /** 2.空指针异常 : NullPointerException。直接输出没有问题。但是调用空指针的变量的功能就会报错！！ */
        String name = null ;
        System.out.println(name); // 直接输出没有问题
        // System.out.println(name.length());  // 此处出现了空指针异常。代码在此处直接执行死亡！

        /** 3.类型转换异常：ClassCastException。 */
        Object o = "齐天大圣";
        //Integer s = (Integer) o;  // 此处出现了类型转换异常。代码在此处直接执行死亡！


        /** 5.数学操作异常：ArithmeticException。 */
        // int c = 10 / 0 ; // 此处出现了数学操作异常。代码在此处直接执行死亡！


        /** 6.数字转换异常： NumberFormatException。 */
        String num = "23aa";
        Integer it = Integer.valueOf(num); // 此处出现了数字转换异常。代码在此处直接执行死亡！
        System.out.println(it+1);

        System.out.println("程序结束。。。。。。");
    }
}