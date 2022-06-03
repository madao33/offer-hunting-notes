package com.itheima._07Map集合练习;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
    目标：输出一个字符串中每个字符出现的次数。（经典面试题）

    分析：
        （1）键盘录入一个字符串。aabbccddaa123。
        （2）定义一个Map集合，键是每个字符，值是其出现的次数。 {a=4 , b=2 ,...}
        （3）遍历字符串中的每一个字符。
        （4）拿着这个字符去Map集合中看是否有这个字符键，有说明之前统计过，其值+1
            没有这个字符键，说明该字符是第一次统计，直接存入“该字符=1”

 */
public class MapDemo01 {
    public static void main(String[] args) {
        // （1）键盘录入一个字符串。aabbccddaa123。
        Scanner scanner = new Scanner(System.in);
        System.out.print("请您输入一个字符串：");
        String datas = scanner.nextLine();

        // （2）定义一个Map集合，键是每个字符，值是其出现的次数。
        Map<Character , Integer> infos = new HashMap<>(); // {a=2,b=2}

        // （3）遍历字符串中的每一个字符。
        // datas = aabbccddaa123
        for(int i = 0 ; i < datas.length() ; i++ ){
            // 取出当前索引的字符
            char ch = datas.charAt(i);
            // （4）拿着这个字符去Map集合中看是否有这个字符键，有说明之前统计过，其值+1
            //   没有这个字符键，说明该字符是第一次统计，直接存入“该字符=1”
            if(infos.containsKey(ch)){
                // 说明这个字符之前已经出现过，其值+1
                infos.put(ch , infos.get(ch) + 1);
            }else{
                // 说明这个字符第一次出现，直接存入 a=1
                infos.put(ch , 1);
            }
        }
        // （5）输出结果
        System.out.println("结果："+infos);

    }
}
