package com.itheima._13Objects类的使用;
import java.util.Objects;
/**
    目标：Objects类的使用。

    Objects类与Object还是继承关系。
    Objects类是从JDK 1.7开始之后才有的。

    Objects的方法：
        1.public static boolean equals(Object a, Object b)
            -- 比较两个对象的。
            -- 底层进行非空判断，从而可以避免空指针异常。更安全！！推荐使用！！
             public static boolean equals(Object a, Object b) {
                     return a == b || a != null && a.equals(b);
             }
        2.public static boolean isNull(Object obj)
            -- 判断变量是否为null ,为null返回true ,反之！
 */
public class ObjectsDemo {
    public static void main(String[] args) {
        Student s1 = null;
        Student s2 = new Student();
        System.out.println(Objects.equals(s1 , s2)); // 可以避免空指针异常。更安全！！
        // System.out.println(s1.equals(s2)); // 空指针异常

        // 询问s1是否为null 为null返回true ,反之！
        System.out.println(Objects.isNull(s1));
        System.out.println(s1 == null); // 可以直接用==判断也可以！
    }
}

