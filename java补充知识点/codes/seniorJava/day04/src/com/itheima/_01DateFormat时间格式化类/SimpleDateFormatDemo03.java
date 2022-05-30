package com.itheima._01DateFormat时间格式化类;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
    目标：简单日期格式化类SimpleDateFormat解析字符串时间成为日期对象。

    引入：
        Date日期对象 -> 格式化成 -> 喜欢的字符串时间形式。
        时间毫秒值 -> 格式化成 -> 喜欢的字符串时间形式。
        字符串的时间形式 -> 解析成 -> Date日期对象。

        开发中经常会收到字符串的时间，需要转成Date日期对象。
        "2018-10-11 10:10:22"

    SimpleDateFormat简单日期格式化类：
         包：java.text.SimpleDateFormat
         构造器：public SimpleDateFormat(String pattern)：指定时间的格式创建简单日期格式化对象。
         方法：
             -- public String format(Date date):可以把日期对象格式化成我们喜欢的时间形式，返回的是字符串!
             -- public String format(Object time):可以把时间毫秒值格式化成我们喜欢的时间形式，返回的是字符串!
             -- public Date parse(String date) throws ParseException:把字符串的时间解析成日期对象
    小结：
        简单日期格式化对象不仅可以把日期对象和时间毫秒值格式化成我们喜欢的字符串时间形式
        它还可以把 把字符串的时间解析成日期对象
             -- public Date parse(String date) throws ParseException:把字符串的时间解析成日期对象
 */
public class SimpleDateFormatDemo03 {
    public static void main(String[] args) throws ParseException {
        // 面试题：请问 “2019-11-04 09:30:30” 往后 1天15小时，30分29s后的时间是多少
        // a.定义一个字符串时间
        String date = "2019-11-04 09:30:30";

        // b.把字符串的时间解析成Date日期对象 。（重点）
        // 1.创建一个简单日期格式化对象负责解析字符串的时间成为日期对象
        // 注意：参数必须与被解析的时间的格式完全一致，否则执行报错！！
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 2.开始解析成日期对象
        Date newDate = sdf.parse(date);

        // c.得到日期对象的时间毫秒值 + 往后走 1天15小时，30分29s
        long time = newDate.getTime() + (24L *60*60 + 15*60*60 + 30*60 + 29) * 1000;

        // d.把时间毫秒值格式化成喜欢的字符串的时间形式!
        System.out.println(sdf.format(time));
    }
}
