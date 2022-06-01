package com.itheima._04Set系列集合;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
    目标:LinkedHashSet

    是HashSet的子类，元素是“有序” 不重复，无索引.

    LinkedHashSet底层依然是使用哈希表存储元素的，
    但是每个元素都额外带一个链来维护添加顺序！！
    不光增删查快，还有序。缺点是多了一个存储顺序的链会占内存空间!!而且不允许重复，无索引。

    总结：
         如果希望元素可以重复，又有索引，查询要快用ArrayList集合。（用的最多）
         如果希望元素可以重复，又有索引，增删要快要用LinkedList集合。（适合查询元素比较少的情况，经常要首尾操作元素的情况）
         如果希望增删改查都很快，但是元素不重复以及无序无索引，那么用HashSet集合。
         如果希望增删改查都很快且有序，但是元素不重复以及无索引，那么用LinkedHashSet集合。
*/
public class HashSetDemo04 {
    public static void main(String[] args) {
        // 有序不重复无索引
        Set<String> sets = new LinkedHashSet<>();
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
