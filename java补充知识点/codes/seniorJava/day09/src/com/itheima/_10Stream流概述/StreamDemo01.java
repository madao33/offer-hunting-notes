package com.itheima._10Stream流概述;

import java.util.ArrayList;
import java.util.List;

/**
    目标： 演示Stream流的强大。

    什么是Stream流？
        在Java 8中，得益于Lambda所带来的函数式编程，
        引入了一个全新的Stream流概念 ，用于解决已有集合/数组类库有的弊端。

    Stream流能解决什么问题?
        可以解决已有集合类库或者数组API的弊端。
        Stream认为集合和数组操作的API很不好用，所以采用了Stream流简化集合和数组的操作！！

    小结：
        Stream流是用来简化集合类库或者数组API的弊端。
        Stream流其实就一根传送带，元素在上面可以被Stream流操作。
 */
public class StreamDemo01 {
    public static void main(String[] args) {
        // 需求：从集合中筛选出所有姓张的人出来。然后再找出姓名长度是3的人。
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");

        list.stream().filter(s -> s.startsWith("张")).filter( s -> s.length()== 3 )
                .forEach(System.out::println);

//        // a.先找出姓张的人。
//        List<String> zhangLists = new ArrayList<>();
//        for (String s : list) {
//            if(s.startsWith("张")){
//                zhangLists.add(s);
//            }
//        }
//        System.out.println(zhangLists);
//
//        // b.张姓姓名长度为3的人
//        List<String> zhangThreeLists = new ArrayList<>();
//        for (String s : zhangLists){
//            if(s.length() == 3){
//                zhangThreeLists.add(s);
//            }
//        }
//        System.out.println(zhangThreeLists);
    }
}
