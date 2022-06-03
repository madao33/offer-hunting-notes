package com.itheima._03Map集合的遍历;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
    目标：Map集合的遍历方式。

    Map集合的遍历方式有：3种。
        （1）“键找值”的方式遍历：先获取Map集合全部的键，再根据键找值。
        （2）“键值对”的方式遍历：难度较大。
        （3）JDK 1.8开始之后的新技术：Lambda表达式。

    b.“键值对”的方式遍历：
        1.把Map集合转换成一个Set集合:Set<Map.Entry<K, V>> entrySet();
        2.此时键值对元素的类型就确定了，类型是键值对实体类型：Map.Entry<K, V>
        3.接下来就可以用foreach遍历这个Set集合，类型用Map.Entry<K, V>
 */
public class MapDemo02 {
    public static void main(String[] args) {
        Map<String , Integer> maps = new HashMap<>();
        // 1.添加元素: 无序，不重复，无索引。
        maps.put("娃娃",30);
        maps.put("iphoneX",100);//  Map集合后面重复的键对应的元素会覆盖前面重复的整个元素！
        maps.put("huawei",1000);
        maps.put("生活用品",10);
        maps.put("手表",10);
        System.out.println(maps);
        // maps = {huawei=1000, 手表=10, 生活用品=10, iphoneX=100, 娃娃=30}
        //
        /**
            “键值对”的方式遍历：更加面向对象的方式，代码复杂。

            “键值对”想把键值对当成一个整体遍历，也就是直接使用foreac遍历：
             for(被遍历集合的元素类型 变量：集合名称){

             }
             但是发现Map集合的键值对数据直接是没有元素类型的，foreach无法直接遍历Map集合。
                👇
             把Map集合通过代码Set<Map.Entry<K, V>> entrySet()转换成Set集合。
                👇
             Set<Map.Entry<String,Integer>> entries = maps.entrySet();
                👇
             entries = [(huawei=1000), (手表=10), (生活用品=10), (iphoneX=100), (娃娃=30) ]
             //                         entry
             此时键值对元素才能作为一个整体就有了类型。类型是 Map.Entry<String,Integer>实体类型
         */
        Set<Map.Entry<String,Integer>> entries = maps.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
             String key = entry.getKey();
             Integer value = entry.getValue();
             System.out.println(key + "=>" + value);
        }
    }
}
