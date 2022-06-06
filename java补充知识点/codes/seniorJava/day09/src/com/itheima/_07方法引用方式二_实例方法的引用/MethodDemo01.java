package com.itheima._07方法引用方式二_实例方法的引用;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
     目标：方法引用有四种形式：
     1.静态方法的引用。
     2.实例方法的引用。
     3.特定类型方法的引用。
     4.构造器引用。

     2.实例方法的引用
          格式： 对象::实例方法。
          简化步骤：
              a.定义一个实例方法，把需要的代码放到实例方法中去。
          实例方法引用的注意事项
             ” 重要：被引用的方法的参数列表要和函数式接口中的抽象方法的参数列表一致。“
 
 */
public class MethodDemo01 {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add("java1");
        lists.add("java2");
        lists.add("java3");

        // 对象是 System.out = new PrintStream();
        // 实例方法：println()
        // 前后参数正好都是一个
        lists.forEach(s -> System.out.println(s));
        lists.forEach(System.out::println);
    }
}