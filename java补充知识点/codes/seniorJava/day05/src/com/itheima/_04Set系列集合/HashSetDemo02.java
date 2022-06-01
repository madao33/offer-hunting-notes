package com.itheima._04Set系列集合;

import java.util.HashSet;
import java.util.Set;

/**
    目标：Set系列集合元素去重复的流程。

    集合和泛型都只能支持引用数据类型。

    1.对于有值特性的，Set集合可以直接判断进行去重复。
    2.对于引用数据类型的类对象，Set集合是按照如下流程进行是否重复的判断。
        Set集合会让两两对象，先调用自己的hashCode()方法得到彼此的哈希值（所谓的内存地址）
        然后比较两个对象的哈希值是否相同，如果不相同则直接认为两个对象不重复。
        如果哈希值相同，会继续让两个对象进行equals比较内容是否相同，如果相同认为真的重复了
        如果不相同认为不重复。

                Set集合会先让对象调用hashCode()方法获取两个对象的哈希值比较
                   /                     \
                false                    true
                /                          \
            不重复                        继续让两个对象进行equals比较
                                           /          \
                                         false        true
                                          /             \
                                        不重复          重复了

        需求：只要对象内容一样，就希望集合认为它们重复了。重写hashCode和equals方法。

    小结：
        如果希望Set集合认为两个对象只要内容一样就重复了，必须重写对象的hashCode和equals方法。
 */
public class HashSetDemo02 {
    public static void main(String[] args) {
        Set<Integer> sets = new HashSet<>(); // 一行经典代码！！
        sets.add(1);
        sets.add(1);
        sets.add(2);
        sets.add(2);
        System.out.println(sets);

        // 存储一些自定义类型数据:无序不重复
        Set<Apple> apples = new HashSet<>();
        Apple a1 = new Apple("红富士",59.9 ,"红色");
        Apple a2 = new Apple("阿克苏",39.9 ,"青红色");
        Apple a3 = new Apple("阿克苏",39.9 ,"青红色");
        System.out.println(a1.hashCode()); // 哈希值，相当于是内存地址
        System.out.println(a2.hashCode()); // 哈希值，相当于是内存地址
        System.out.println(a3.hashCode()); // 哈希值，相当于是内存地址
        apples.add(a1);
        apples.add(a2);
        apples.add(a3);
        System.out.println(apples);

    }
}