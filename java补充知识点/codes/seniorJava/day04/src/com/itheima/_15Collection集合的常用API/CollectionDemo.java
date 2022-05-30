package com.itheima._15Collection集合的常用API;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/**
    目标：Collection集合的常用API.
    
    Collection是集合的祖宗类，它的功能是全部集合都可以继承使用的，所以要学习它。
    Collection API如下：
         - public boolean add(E e)：  把给定的对象添加到当前集合中 。
         - public void clear() :清空集合中所有的元素。
         - public boolean remove(E e): 把给定的对象在当前集合中删除。
         - public boolean contains(Object obj): 判断当前集合中是否包含给定的对象。
         - public boolean isEmpty(): 判断当前集合是否为空。
         - public int size(): 返回集合中元素的个数。
         - public Object[] toArray(): 把集合中的元素，存储到数组中
    小结：
        记住以上API。
 */
public class CollectionDemo {
    public static void main(String[] args) {
        // HashSet:添加的元素是无序，不重复，无索引。
        Collection<String> sets = new HashSet<>();
        // 1.添加元素，添加成功返回true.
        System.out.println(sets.add("贾乃亮")); // true
        System.out.println(sets.add("贾乃亮")); // false
        System.out.println(sets.add("王宝强")); // true
        sets.add("陈羽凡");
        System.out.println(sets); // 集合重写了toString()方法，默认打印出内容信息
        // 2.清空集合的元素。
        //sets.clear();
        //System.out.println(sets);

        // 3.判断集合是否为空 是空返回true 反之
        System.out.println(sets.isEmpty()); // false

        // 4.获取集合的大小
        System.out.println(sets.size()); // 3

        // 5.判断集合中是否包含某个元素 。
        System.out.println(sets.contains("贾乃亮"));

        // 6.删除某个元素:如果有多个重复元素默认删除前面的第一个！
        sets.remove("陈羽凡");
        System.out.println(sets);

        // 7.把集合转换成数组
        Object[] arrs = sets.toArray();
        System.out.println("数组："+ Arrays.toString(arrs));

        String[] arrs1 = sets.toArray(String[]::new); // 以后再了解，指定转换的数组类型！
        System.out.println("数组："+ Arrays.toString(arrs1));

        System.out.println("---------------------拓展---------------------------");
        Collection<String> c1 = new ArrayList<>();
        c1.add("李小璐");
        c1.add("马蓉");

        Collection<String> c2 = new ArrayList<>();
        c2.add("白百合");

        c1.addAll(c2); // 把c2集合的元素全部倒入到c1
        System.out.println(c1);
    }
}
