package com.itheima._12Object类的详细使用;

/**
    目标：常用API的学习-Object类的toString方法使用详解。

    引入
        包：java.lang.Object
        Object类是Java中的祖宗类。
        一个类要么默认继承了Object类，要么间接继承了Object类。
        Object类的方法是一切子类都可以直接使用的，所以我们要学习Object类的方法。

    Object类的常用方法：
        （1）public String toString():
                -- 默认是返回当前对象在堆内存中的地址信息：
                    com.itheima._12Object类的详细使用.Student@735b478
                -- 默认的地址信息格式：类的全限名@内存地址
                -- 直接输出对象名称，默认会自动调用toString()方法，所以输出对象toString()调用可以省略不写
                -- 开发中直接输出对象，默认输出对象的地址其实是毫无意义的。
                   开发中输出对象变量，更多的时候是希望看到对象的内容数据而不是对象的地址信息！
                   所以父类toString()方法存在的意义就是为了被子类重写，以便
                    返回对象的内容信息输出！！
              小结:
                toString()默认是返回当前对象在堆内存中的地址信息：
                开发中输出对象变量，更多的时候是希望看到对象的内容数据而不是对象的地址信息！
                所以父类toString()方法存在的意义就是为了被子类重写，重写toString可以看到对象的内容信息。

         （2）public boolean equals(Object o):

 */
public class ObjectToStringDemo01 {
    public static void main(String[] args) {
        Student zs = new Student("张森",21 , '男');
        // 直接调用toString方法返回的是对象在内存重点的地址
        // System.out.println(zs.toString());
        // 直接输出对象名称，默认会自动调用toString()方法，所以输出对象toString()调用可以省略不写
        System.out.println(zs);
    }
}
