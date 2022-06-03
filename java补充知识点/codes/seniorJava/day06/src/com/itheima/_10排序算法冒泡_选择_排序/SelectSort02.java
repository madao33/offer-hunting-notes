package com.itheima._10排序算法冒泡_选择_排序;

import java.util.Arrays;

/**
    目标：选择排序。

    选择排序的思想：从当前位置开始找出后面的较小值与该位置交换。
    数组：int[] arr = {5 , 1 , 3 , 2}

    选择排序的实现思路：
        （1）控制选择几轮：数组的长度-1.
        （2）控制每轮从当前位置开始比较几次。

            i(轮数)     次数
            0           3
            1           2
            2           1
    小结：
        从当前位置开始找出后面的较小值与该位置交换。
        控制选择几轮：数组的长度-1.
        以当前位置作为基准，从下一个元素开始遍历寻找出较小值与当前位置交换即可！
 */
public class SelectSort02 {
    public static void main(String[] args) {
        int[] arr = {5 , 1 , 3 , 2};
        // 1.定义一个循环控制选择几轮
        for(int i = 0 ; i < arr.length - 1 ; i++ ){
            // 2.定义一个循环控制每轮比较几次，一定是以当前位置与后面元素比较
            // i =0  j = 1 2 3
            // i =1  j = 2 3
            // i =2  j = 3
            // 遍历后面的元素
            for(int j = i+1 ; j < arr.length ; j++ ){
                // 拿当前位置与j指定的元素进行大小比较，后面的较小就交换位置
                if(arr[j] < arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("数组："+ Arrays.toString(arr));
    }
}
