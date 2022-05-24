package com.itheima._12Object类的详细使用;

/**
    目标：常用API的学习-Object类的equals方法使用详解。

    包：java.lang.Object
    Object类是Java中的祖宗类。
    一个类要么默认继承了Object类，要么间接继承了Object类。
    Object类的方法是一切子类都可以直接使用的，所以我们要学习Object类的方法。

    Object类的常用方法：
        （1）public String toString():
                -- 默认是返回当前对象在堆内存中的地址信息：com.itheima._12Object类的详细使用.Student@735b478
                -- 默认的地址信息格式：类的全限名@内存地址
                -- 直接输出对象名称，默认会调用toString()方法，所以直接输出对象可以省略toString()不写。
                -- 实际开发中直接输出对象，输出对象的地址其实是没有意义的。
                   所以toString方法存在的意义是为了被子类重写。
                   以便能够返回对象的数据内容输出。因为实际开发中我们
                    输出对象更多的时候是希望看到对象的数据内容信息。

                小结：开发中如果希望输出对象看到对象的内容，
                     只需要重写toString()方法即可。
                     所以toString方法存在的意义是为了被子类重写。

        （2）public boolean equals(Object o):
                -- 默认是比较两个对象的地址是否相同。相同返回true,反之。
                -- 直接比较两个对象的地址是否相同完全可以用“==”替代equals。
                   所以equals存在的意义是为了被子类重写，以便程序员可以
                    自己来定制比较规则。
                -- 需求：只要两个对象的内容一样，我们就认为他们是相等的。
            小结：
                 equals存在的意义是为了被子类重写，以便程序员可以
                 自己来定制比较规则。
 */
public class ObjectEqualsDemo02 {
    public static void main(String[] args) {
        Student zs1 = new Student("张森",21 , '男');
        Student zs2 = new Student("张森",21 , '男');
        System.out.println(zs1.equals(zs2)); // true
        System.out.println(zs1 == zs2); // false
    }
}
