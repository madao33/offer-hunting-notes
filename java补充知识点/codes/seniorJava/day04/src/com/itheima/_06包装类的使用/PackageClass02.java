package com.itheima._06包装类的使用;

/**
    目标：包装类的特殊功能。

    Java为包装类做了一些特殊功能，以便程序员使用。
    包装类作为类首先拥有了Object类的方法。
    包装类作为引用类型的变量可以存储null值。

    具体来看特殊功能主要有：
        1.可以把基本数据类型的值转换成字符串类型的值。（没啥用）
            -- 调用toString()方法。
            -- 调用Integer.toString(基本数据类型的值)得到字符串。
            -- 直接把基本数据类型+空字符串就得到了字符串。

        2.把字符串类型的数值转换成对应的基本数据类型的值。（真的很有用）
            -- Xxx.parseXxx("字符串类型的数值")
            -- Xxx.valueOf("字符串类型的数值"):推荐使用!
    小结：
        包装类可以把字符串类型的数值转换成对应的基本数据类型的值（真的很有用）
 */
public class PackageClass02 {
    public static void main(String[] args) {
        // 1.把基本数据类型的值转成字符串
        Integer it = 100 ;
        // a.调用toString()方法。
        String itStr = it.toString();
        System.out.println(itStr+1);
        // b.调用Integer.toString(基本数据类型的值)得到字符串。
        String itStr1 = Integer.toString(it);
        System.out.println(itStr1+1);
        // c.直接把基本数据类型+空字符串就得到了字符串。
        String itStr2 = it+"";
        System.out.println(itStr2+1);

        // 2.把字符串类型的数值转换成对应的基本数据类型的值。（真的很有用）
        String numStr = "23";
        //int numInt = Integer.parseInt(numStr);
        int numInt = Integer.valueOf(numStr);
        System.out.println(numInt+1);

        String doubleStr = "99.9";
        //double doubleDb = Double.parseDouble(doubleStr);
        double doubleDb = Double.valueOf(doubleStr);
        System.out.println(doubleDb+0.1);
    }
}
