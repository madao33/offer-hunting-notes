package com.itheima._21递归的概述和应用理论;

/**
     目标：递归实现1-n的和。

     f(n) = 1 + 2 + 3 + 4 + 5 + 6 + ...n-1 + n ;
     f(n) = f(n-1) + n

     流程：
         f(5) = return f(4)  + 5  = 1 + 2 + 3 + 4 + 5
         f(4) = return f(3)  + 4  = 1 + 2 + 3 + 4
         f(3) = return f(2)  + 3  = 1 + 2 + 3
         f(2) = return f(1)  + 2  = 1 + 2
         f(1) = return 1

     递归的核心三要素：
        （1）递归的终点接： f(1) = 1
        （2）递归的公式：  f(n) = f(n-1) + n
        （3）递归的方向必须走向终结点：
 */
public class RecursionDemo05 {
    public static void main(String[] args) {
        System.out.println(f(5));
    }

    public static int f(int n){
        if(n == 1 ) return 1;
        return f(n-1) + n;
    }
}
