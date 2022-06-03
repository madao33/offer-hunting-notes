package com.itheima._17线程安全;
// 账户类
public class Account {
    private String cardID;
    private double moeny;

    // 小明线程/小红线程
    public void drawMoney(double moeny) {
        // 开始判断取钱逻辑
        // 1.先知道是谁来取钱
        String name = Thread.currentThread().getName();
        // 2.判断余额是否足够
        if(this.moeny >= moeny){
            System.out.println(name+"来取钱，余额足够，吐出"+moeny);
            // 3.更新余额
            this.moeny -= moeny;
            System.out.println(name+"来取钱后，余额剩余"+ this.moeny);
        }else{
            System.out.println(name+"来取钱，余额不足！");
        }
    }

    public Account() {
    }

    public Account(String cardID, double moeny) {
        this.cardID = cardID;
        this.moeny = moeny;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public double getMoeny() {
        return moeny;
    }

    public void setMoeny(double moeny) {
        this.moeny = moeny;
    }



}
