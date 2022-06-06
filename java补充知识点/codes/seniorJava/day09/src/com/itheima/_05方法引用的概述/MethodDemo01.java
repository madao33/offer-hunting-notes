package com.itheima._05方法引用的概述;
import java.util.ArrayList;
import java.util.List;
/**
     目标：方法引用的概述。(了解)

     方法引用：
             方法引用是为了进一步简化Lambda表达式的写法。
             方法引用的格式：类型或者对象::引用的方法。
     关键语法是：“::”

     小结：
        方法引用可以进一步简化Lambda表达式的写法。
        关键语法是：“::”
*/
public class MethodDemo01 {
    public static void main(String[] args) {
        List<String> lists = new ArrayList<>();
        lists.add("java1");
        lists.add("java2");
        lists.add("java3");

        lists.forEach( s -> System.out.println(s));
        // 方法引用！
        lists.forEach(System.out::println);


    }
}
