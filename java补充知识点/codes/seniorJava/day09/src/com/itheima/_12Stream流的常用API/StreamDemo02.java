package com.itheima._12Stream流的常用API;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
    目标：Stream流的常用API
            -- Stream<T> filter(Predicate<? super T> predicate)
         forEach : 逐一处理
         count：统计个数
            -- long count();
         filter : 过滤元素
         limit : 取前几个元素
         skip : 跳过前几个
         map （映射）: 加工方法（把原来的元素加工以后，从新放上去）
            -- <R> Stream<R> map(Function<? super T, ? extends R> mapper);
         concat : 合并流
 */
public class StreamDemo02 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.add("张三丰");

        // 需求：把名称都加上“黑马的:+xxx”
        list.stream().map(a -> "黑马的："+a).forEach(System.out::println);

        // 需求：把名称都加工厂学生对象放上去!!
        // list.stream().map(name -> new Student(name)).forEach(System.out::println);

        list.stream().map(Student::new).forEach(System.out::println);
    }
}
