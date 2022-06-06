package com.itheima._13Stream流的综合应用;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
    目标：综合案例
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<String> one = new ArrayList<>();
        one.add("迪丽热巴");
        one.add("宋远桥");
        one.add("苏星河");
        one.add("老子");
        one.add("庄子");
        one.add("孙子");
        one.add("洪七公");

        List<String> two = new ArrayList<>();
        two.add("古力娜扎");
        two.add("张无忌");
        two.add("张三丰");
        two.add("赵丽颖");
        two.add("张二狗");
        two.add("张天爱");
        two.add("张三");

        /**
         * 1. 第一个队伍只要名字为3个字的成员姓名；
         * 2. 第一个队伍筛选之后只要前3个人；
         */
        Stream<String> oneStream =
                one.stream().filter(s -> s.length() == 3).limit(3);

        /**
         * 3. 第二个队伍只要姓张的成员姓名；
         * 4. 第二个队伍筛选之后不要前2个人；
         * 5. 将两个队伍合并为一个队伍；
         */
        Stream<String> twoStream =
                two.stream().filter(s -> s.startsWith("张")).skip(2);
        Stream<String> allStream = Stream.concat(oneStream , twoStream);

        /**
         * 6. 根据姓名创建`Student`对象； (加工)
         * 7. 打印整个队伍的Student对象信息。
         */
        //allStream.map(s -> new Student(s)).forEach(System.out::println);
        allStream.map(Student::new).forEach(System.out::println);
    }
}
