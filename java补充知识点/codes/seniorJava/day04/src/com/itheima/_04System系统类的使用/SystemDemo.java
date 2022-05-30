package com.itheima._04System系统类的使用;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
    目标：System系统类的使用。
    System代表当前系统。
    静态方法：
        1.public static void exit(int status):终止JVM虚拟机，非0是异常终止。
        2.public static long currentTimeMillis():获取当前系统此刻时间毫秒值。
        3.可以做数组的拷贝。
          arraycopy(Object var0, int var1, Object var2, int var3, int var4);
         * 参数一：原数组
         * 参数二：从原数组的哪个位置开始赋值。
         * 参数三：目标数组
         * 参数四：赋值到目标数组的哪个位置
         * 参数五：赋值几个。
 */
public class SystemDemo {
    public static void main(String[] args) {
        System.out.println("程序开始。。。");

         // 1.终止当前虚拟机
        //System.exit(0); // 0代表正常终止!!

        // 2.得到系统当前时间毫秒值
        long time = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println(sdf.format(time));

        // 3.可以做数组的拷贝（了解）
        int[] arrs1 = new int[]{10 ,20 ,30 ,40 ,50 ,60 ,70};
        int[] arrs2 = new int[6]; // [ 0 , 0 , 0 , 0 , 0 , 0]
        // arrs2 = [0 , 30 , 40 , 50 , 0 , 0 ]
        /**
         arraycopy(Object src,int  srcPos ,Object dest, int destPos,  int length)
         参数一：原数组
         参数二：从哪个索引位置开始赋值
         参数三：目标数组
         参数四：目标数组的开始索引：
         参数五：复制几个
         */
        System.arraycopy(arrs1,2, arrs2 , 1 , 3);
        System.out.println(Arrays.toString(arrs2));

        System.out.println("程序结束。。。");
    }
}
