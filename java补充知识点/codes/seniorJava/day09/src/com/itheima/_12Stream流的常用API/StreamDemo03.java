package com.itheima._12Stream流的常用API;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
    目标：Stream流的常用API
            -- Stream<T> filter(Predicate<? super T> predicate)
         forEach : 逐一处理
         count：统计个数
            -- long count();
         filter : 过滤元素
         limit : 取前几个元素
         skip : 跳过前几个
         map : 加工方法（把原来的元素加工以后，从新放上去）
         concat : 合并流
            -- public static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)
 */
public class StreamDemo03 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.add("张三丰");

        // 数组流
        Stream<Integer> s1 = Stream.of(10, 20 ,30 ,40);
        // 集合流
        Stream<String> s2 = list.stream();
        // 合并流
        Stream<Object> s3 = Stream.concat(s1,s2);
        s3.forEach(System.out::println);
    }
}
