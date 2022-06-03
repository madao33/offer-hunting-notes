package com.itheima._17线程安全;
// 线程类：创建取钱线程对象的。
public class DrawThread extends Thread{
    // 定义一个成员变量接收账户对象
    private Account acc ;
    public DrawThread(Account acc,String name){
        super(name);
        this.acc = acc;
    }
    @Override
    public void run() {
        // 小明 小红
        // 取钱100000
        acc.drawMoney(100000);
    }
}
