package com.itheima._05LinkedHashMap的特点;
import java.util.*;

/**
    目标：LinkedHashMap的特点介绍。

    LinkedHashMap是HashMap的子类。
        -- 添加的元素按照键有序，不重复的。
    HashSet集合相当于是HashMap集合的键都不带值。
    LinkedHashSet集合相当于是LinkedHashMap集合的键都不带值。

    底层原理完全一样，都是基于哈希表按照键存储数据的，
    只是HashMap或者LinkedHashMap的键都多一个附属值。


    小结：
        HashMap集合是无序不重复的键值对集合。
        LinkedHashMap集合是有序不重复的键值对集合。
        他们都是基于哈希表存储数据,增删改查都很好。

 */
public class LinkedHashMapDemo {
    public static void main(String[] args) {
        Map<String , Integer> maps = new LinkedHashMap<>();
        maps.put("iphoneX",10);
        maps.put("娃娃",30);
        maps.put("iphoneX",100); // 依然是保留前面的位置，只是替换其值！
        maps.put("huawei",1000);
        maps.put("生活用品",10);
        maps.put("手表",10);
        System.out.println(maps);
    }
}
