package com.itheima._03Math类的详细使用;

/**
    目标：Math类的使用。

    Math用于做数学运算。
    Math类中的方法全部是静态方法，直接用类名调用即可。
    方法：
          方法名                                        说明                
          public static int abs(int a)                  获取参数a的绝对值：
          public static double ceil(double a)           向上取整
          public static double floor(double a)          向下取整
          public static double pow(double a, double b)  获取a的b次幂        
          public static long round(double a)            四舍五入取整
    小结：
        记住。
 */
public class MathDemo {
    public static void main(String[] args) {
        // 1.取绝对值:返回正数。
        System.out.println(Math.abs(10));
        System.out.println(Math.abs(-10.3));
        // 2.向上取整: 5
        System.out.println(Math.ceil(4.00000001)); // 5.0
        // 3.向下取整：4
        System.out.println(Math.floor(4.99999999)); // 4.0
        // 4.求指数次方
        System.out.println(Math.pow(2 , 3)); // 2^3 = 8.0
        // 5.四舍五入 10
        System.out.println(Math.round(4.49999)); // 4
        System.out.println(Math.round(4.500001)); // 5
    }
}
