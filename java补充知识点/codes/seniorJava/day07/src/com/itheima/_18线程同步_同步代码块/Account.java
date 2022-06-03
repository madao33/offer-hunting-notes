package com.itheima._18线程同步_同步代码块;
// 账户类
public class Account {
    private double money ; // 余额
    private String cardId ;
    public Account(){

    }
    public Account(double money, String cardId) {
        this.money = money;
        this.cardId = cardId;
    }
    // 小明、小红执行到此
    public void drawMoney(double money) {
        // 1.先拿到是谁来取钱：取当前线程对象的名称
        String name = Thread.currentThread().getName();
        // 小明、小红
        // this == 当前共享账户acc
        // 2.判断余额是否足够
        synchronized (this){
            if(this.money >= money){
                // 3.余额足够
                System.out.println(name+"来取钱，吐出："+money);
                // 4.更新余额
                this.money -= money;
                // 5.输出结果
                System.out.println(name+"来取钱"+money+"成功，取钱后剩余："+this.money);
            }else{
                // 6.余额不足
                System.out.println(name+"来取钱，余额不足，剩余"+this.money);
            }
        }
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }


}
