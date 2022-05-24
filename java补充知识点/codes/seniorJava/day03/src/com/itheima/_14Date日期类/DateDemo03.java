package com.itheima._14Date日期类;


import java.util.Date;

/**
    目标:Date类的有参数构造器的使用。

    构造器：
         -- public Date():创建当前系统的此刻日期时间对象。
         -- public Date(long time):把时间毫秒值转换成日期对象。
    流程：
        Date日期对象 -> getTime() -> 时间毫秒值
        时间毫秒值 -> new Date(时间毫秒值) -> Date日期对象
    小结：
        public Date(long time):把时间毫秒值转换成日期对象。
 */
public class DateDemo03 {
    public static void main(String[] args) {
        // 需求：问121s以后的时间是多少。

        // 1.拿到此刻日期对象
        Date d = new Date();
        System.out.println(d);

        // 2.拿到此刻日期对象的时间毫秒值 往后走 121 s
        long time = d.getTime() + 121*1000;

        // 3.把时间毫秒值转换成日期对象。
        Date d1 = new Date(time);
        System.out.println(d1);
    }
}
