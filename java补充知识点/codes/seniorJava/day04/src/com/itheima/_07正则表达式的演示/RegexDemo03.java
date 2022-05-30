package com.itheima._07正则表达式的演示;

import java.util.Scanner;

/**
    目标：正则表达式的实际应用。

    0203433535
    020-4343234234

 */
public class RegexDemo03 {
    public static void main(String[] args) {
        checkEmail();
        //checkTel();
        //checkPhone();
    }

    private static void checkPhone() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请您输入电话号码：");
        String phone = sc.nextLine();
        if(phone.matches("0\\d{2,5}-?\\d{5,15}")){
            System.out.println("电话号码合法了！");
        }else{
            System.err.println("电话号码不正确！");
        }
    }


    private static void checkTel() {
        Scanner sc = new Scanner(System.in);
        System.out.print("请您输入手机号码：");
        String tel = sc.nextLine();
        if(tel.matches("1[3-9]\\d{9}")){
            System.out.println("手机号码合法了！");
        }else{
            System.err.println("手机号码不正确！");
        }
    }

    // 校验邮箱
    public static void checkEmail(){
        Scanner sc = new Scanner(System.in);
        System.out.print("请您输入邮箱：");
        String email = sc.nextLine();
        // 3232323@qq.com
        // dlei082@163.com
        // dlei@pic.com.cn
        if(email.matches("\\w{1,}@\\w{2,10}(\\.\\w{2,10}){1,2}")){
            System.out.println("邮箱合法了！");
        }else{
            System.err.println("邮箱格式不正确！");
        }
    }
}
