package com.itheima._14线程的常用API;

/**
    目标：线程休眠API.

    public static void sleep(long time): 让当前线程休眠多少毫秒再继续执行。


 */
public class ThreadDemo02 {
    public static void main(String[] args) {
        for(int i = 0 ; i < 10 ; i++ ) {
            System.out.println(i);
            try {
                // 项目经理让我加上这行代码
                // 如果用户交钱了，我就去掉。
                Thread.sleep(1000); // 让当前线程休眠1s.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
