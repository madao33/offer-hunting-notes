package com.itheima._10内部类_匿名内部类_重点;

/**
    目标： 匿名内部类的使用形式。(关注语法)

    需求：很多角色要一起参加游泳比赛（老师，学生，运动员）

 */
public class Anonymity02 {
    public static void main(String[] args) {
        Swim bozai = new Swim() {
            @Override
            public void swimming() {
                System.out.println("老师🏊‍的贼溜~~~~");
            }
        };
        go(bozai);

        Swim boniu = new Swim() {
            @Override
            public void swimming() {
                System.out.println("波妞学生快乐的狗爬式~~~");
            }
        };
        go(boniu);

        go(new Swim() {
            @Override
            public void swimming() {
                System.out.println("波妞学生快乐的狗爬式~~~");
            }
        });
    }

    // 提供一个方法让全部角色进入比赛
    public static void go(Swim s){
        System.out.println("开始。。。。");
        s.swimming();
        System.out.println("结束。。。。");
    }
}

interface Swim{
    void swimming();
}

