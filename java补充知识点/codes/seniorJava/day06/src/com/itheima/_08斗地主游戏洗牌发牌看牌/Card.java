package com.itheima._08斗地主游戏洗牌发牌看牌;

public class Card {
    private String number ;
    private String color ;

    public Card() {
    }

    public Card(String number, String color) {
        this.number = number;
        this.color = color;
    }

    /**
     * 获取
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取
     * @return color
     */
    public String getColor() {
        return color;
    }

    /**
     * 设置
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return number+color;
    }
}
