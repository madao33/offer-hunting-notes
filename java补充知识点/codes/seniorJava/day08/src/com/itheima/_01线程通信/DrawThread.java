package com.itheima._01线程通信;

/**
    取钱的线程类
 */
public class DrawThread extends Thread {
    private Account acc ; // 定义了一个账户类型的成员变量接收取款的账户对象！
    public DrawThread(Account acc , String name){
        super(name); // 为当前线程对象取名字
        this.acc = acc ;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(4000);
                acc.drawMoney(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
