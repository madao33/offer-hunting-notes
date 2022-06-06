package com.itheima._15收集Stream流;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
    目标：收集Stream流:把Stream流的数据转回成集合。

    引入：
        Stream的作用是：把集合转换成一根传送带，借用Stream流的强大功能进行的操作。
        但是实际开发中数据最终的形式还是应该是集合，最终Stream流操作完毕以后还是要转换成集合。
        这就是收集Stream流。

    收集Stream流的含义：就是把Stream流的数据转回到集合中去。

    Stream流：手段。
    集合：才是目的。

    小结：
        收集Stream流的含义：就是把Stream流的数据转回到集合中去。

    熟练函数式编程，如Lambda表达式简化函数式接口的匿名内部类形式，
    以及四种方法引用简化Lambda表达式，以及流式编程思想如Stream简化集合或者数组的操作。
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.add("张三丰");

        Stream<String> zhangLists = list.stream().filter(s -> s.startsWith("张"));
        // 把stream流转换成Set集合。
        Set<String> sets = zhangLists.collect(Collectors.toSet());
        System.out.println(sets);

        // 把stream流转换成List集合。
        Stream<String> zhangLists1 = list.stream().filter(s -> s.startsWith("张"));
        List<String> lists= zhangLists1.collect(Collectors.toList());
        System.out.println(lists);

        // 把stream流转换成数组。
        Stream<String> zhangLists2 = list.stream().filter(s -> s.startsWith("张"));
        Object[] arrs = zhangLists2.toArray();
        // 可以借用构造器引用申明转换成的数组类型！！！
        String[] arrs1 = zhangLists2.toArray(String[]::new);
    }
}






