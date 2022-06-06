package com.itheima._00编程思维题目;

/**
    目标：非规律化递归问题，编程思维的拓展。

    啤酒问题：啤酒2元一瓶，4个盖子可以换一瓶，2个空瓶可以换一瓶。
 */
public class BeerDemo01 {
    // 定义一个静态变量存储可以喝酒的总数
    public static int totalNum;
    public static int lastPingZiNum;
    public static int lastGaiZiNum;

    public static void main(String[] args) {
        buyBeer(10);
        System.out.println("总数："+totalNum);
        System.out.println("剩余盖子："+lastGaiZiNum);
        System.out.println("剩余瓶子："+lastPingZiNum);
    }

    // 定义一个方法帮助用户买酒
    public static void buyBeer(int money){
        // a.拿钱买酒
        int number = money / 2 ;
        // 累加起来
        totalNum += number;

        // 算出当前剩余的全部盖子和瓶子数，换算成金额继续购买。
        int currentPingZiNum = lastPingZiNum + number ;
        int currentGaiZiNum = lastGaiZiNum + number ;
        // 把他们换算成金额
        int totalMoney = 0 ;
        totalMoney +=(currentPingZiNum/2)*2;
        //算出剩余的瓶子
        lastPingZiNum = currentPingZiNum % 2 ;

        // 换算盖子
        totalMoney+=(currentGaiZiNum / 4) * 2;
        lastGaiZiNum = currentGaiZiNum % 4 ;

        // 继续拿钱买酒
        if(totalMoney >= 2){
            buyBeer(totalMoney);
        }
    }
}
