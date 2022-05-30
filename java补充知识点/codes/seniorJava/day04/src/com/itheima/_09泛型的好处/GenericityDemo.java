package com.itheima._09泛型的好处;

import java.util.ArrayList;

/**
  目标：泛型的好处。
  泛型在编译阶段约束了操作的数据类型，从而不会出现类型转换异常。
  体现的是Java的严谨性和规范性，数据类型,经常需要进行统一！
 */
public class GenericityDemo {
    public static void main(String[] args) {
        ArrayList<String> lists = new ArrayList<>();
        lists.add("赵敏");
        lists.add("张无忌");
//        lists.add(false);
//        lists.add(99.9);

        for(int i = 0 ; i < lists.size() ; i++ ){
            String ele = lists.get(i);
            System.out.println(ele);
        }

    }
}
