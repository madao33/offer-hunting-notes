package com.itheima._04Map集合存储自定义类型;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
    目标：Map集合存储自定义类型;

    Map集合的键和值都可以存储自定义类型。

    小结：
        Map集合的键和值都可以存储自定义类型。
        如果希望Map集合认为自定义类型的键对象重复了，必须重写对象的hashCode()和equals()方法
 */
public class MapDemo01 {
    public static void main(String[] args) {
        Map<Orange,String> maps = new HashMap<>();
        Orange o1 = new Orange("黄橘子",20.3 , "贼便宜！");
        Orange o2 = new Orange("黑橘子",30.3 , "坏了");
        Orange o3 = new Orange("青橘子",34.3 , "9.9包邮");
        Orange o4 = new Orange("黄橘子",20.3 , "贼便宜！");

        maps.put(o1 , "江西\n");
        maps.put(o2 , "赣州\n");
        maps.put(o3 , "广州\n");
        maps.put(o4 , "广西\n");

        System.out.println(maps);
    }
}
