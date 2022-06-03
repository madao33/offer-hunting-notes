package com.itheima._10异常的强大之处;

import java.util.Scanner;

/**
     拓展:异常的作用
         1.可以处理代码问题，防止程序出现异常后的死亡。
         2.提高了程序的健壮性和安全性。
 */
public class Demo {
    public static void main(String[] args) {

        // 需求:请输入一个合法的年龄为止。  12\r\n
        while(true){
            try{
                Scanner sc = new Scanner(System.in);
                System.out.println("请您输入您的年年龄：");
                int age = sc.nextInt();
                System.out.println("您是："+age);
                break;
            }catch (Exception e){
                System.err.println("您的年龄是瞎输入的！");
            }
        }
    }
}
