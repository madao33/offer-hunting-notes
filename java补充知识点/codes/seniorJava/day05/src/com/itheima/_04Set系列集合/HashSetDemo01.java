package com.itheima._04Set系列集合;
import java.util.*;

/**
  目标：HashSet集合

  Collection集合的体系:
                      Collection<E>(接口)
                /                                       \
             Set<E>(接口)                             List<E>(接口)
              /             \                             /                     \               \
         HashSet<E>(实现类) TreeSet<E>(实现类)       LinkedList<E>(实现类) Vector(线程安全)     ArrayList<E>(实现类)
            /
        LinkedHashSet<E>(实现类)

  Collection集合体系的特点:
         Set系列集合： 添加的元素，是无序，不重复，无索引的。
             -- HashSet：添加的元素，是无序，不重复，无索引的。
             -- LinkedHashSet：添加的元素，是有序，不重复，无索引的。
             -- TreeSet: 不重复，无索引，按照大小默认升序排序!! ( 可排序集合 )
         List系列集合：添加的元素，是有序，可重复，有索引的。
             -- LinkedList： 添加的元素，是有序，可重复，有索引的。底层是基于链表存储数据的，查询慢，增删快
             -- ArrayList： 添加的元素，是有序，可重复，有索引的。底层基于数组存储数据的，查询快，增删慢

    研究两个问题(面试热点)：
        1）Set集合添加的元素是不重复的，是如何去重复的？
        2）Set集合元素无序的原因是什么？

 */
public class HashSetDemo01 {
    public static void main(String[] args) {
        // 无序，不重复，无索引的。
        Set<String> sets = new HashSet<>(); // 一行经典代码！！
        sets.add("Mybatis");
        sets.add("Java");
        sets.add("Java");
        sets.add("MySQL");
        sets.add("MySQL");
        sets.add("Spring");
        // [Java, MySQL, Spring, Mybatis]
        System.out.println(sets);
    }
}
