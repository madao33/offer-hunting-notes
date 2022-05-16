package com.itheima._16枚举的应用;
/**
    拓展：枚举的API
 */
public class EnumDemo03 {
    public static void main(String[] args) {
        Season03 s = Season03.SPRING;
        System.out.println(s); // SPRING
        System.out.println("---------------");
        // 获取当前类的全部枚举实例 ： public static Season[] values()
        Season03[] ss = Season03.values();
        for(int i = 0 ; i < ss.length ; i++ ){
            Season03 s1 = ss[i];
            System.out.println(s1);
        }

        // 获取枚举对象的索引: ordinal()
        Season03 s2 = Season03.AUTUMN;
        System.out.println(s2.ordinal()); // 2
    }
}

enum Season03{
    SPRING, SUMMER , AUTUMN, WINTER ;
}

enum Sex03{
    BOY , GIRL;
}