package com.itheima._08斗地主游戏洗牌发牌看牌;

import java.util.*;

/**
    目标：斗地主游戏的案例开发-Map集合实现。

    业务需求分析:
        斗地主的做牌，洗牌，发牌,排序（拓展知识）, 看牌
        业务:总共有54张牌。
        点数: "3","4","5","6","7","8","9","10","J","Q","K","A","2"
        花色: "♠", "♥", "♣", "♦"
        大小王: "👲" , "🃏"
        点数分别要组合4种花色，大小王各一张。
        斗地主：发出51张牌，剩下3张作为底牌。

    功能：
        1.做牌。
        2.洗牌
        3.定义3个玩家。
        4.发牌。
        5.
        6.看牌。

    用面向对象设计案例：
        a.定义一个牌类，代表牌对象。 一个牌对象代表一张牌。
        b.定义一个集合存储54张牌，集合只需要一个(因为牌只需要一副)
                {card1=0 , card2=1, ......}
 */
public class GameDemo {
    /** 1.定义一个Map集合存储54张牌对象，键是牌对象，值是其大小 用于实现大小排序  */
    public static final Map<Card , Integer> ALL_CARDS_SIZE = new HashMap<>();
    // 真正存储54张牌对象的List集合，用于做牌，洗牌，发牌！
    public static final List<Card> ALL_CARDS = new ArrayList<>();
    /** 2.做牌：静态代码块初始化54张牌对象  */
    static {
        // 1.定义一个数组存储牌的点数，类型确定，个数确定请用数组存储！
        String[] numbers = {"3","4","5","6","7","8","9","10","J","Q","K","A","2"};
        // 2.定义一个数组存储牌的花色，类型确定，个数确定请用数组存储！
        String[] colors = { "♠", "♥", "♣", "♦" };

        // 3.先遍历点数与四种花色组装成牌对象存入到集合中去
        int index = 0;
        for (String number : numbers) {
            // 遍历花色
            for (String color : colors) {
                // 创建一张牌对象封装点数和花色
                Card card = new Card(number , color);
                ALL_CARDS.add(card);
                ALL_CARDS_SIZE.put(card , index++);
            }
        }
        Card c1 = new Card("","🃏");
        Card c2 = new Card("", "👲");

        ALL_CARDS.add(c1);
        ALL_CARDS.add(c2);
        ALL_CARDS_SIZE.put(c1 , index++);
        ALL_CARDS_SIZE.put(c2 , index++);
        System.out.println("新牌："+ ALL_CARDS);
    }

    public static void main(String[] args) {
        /**
         c.洗牌(把新牌的牌顺序打乱)
         */
        Collections.shuffle(ALL_CARDS);
        System.out.println("洗牌后："+ALL_CARDS);

        /**
         d.定义3个玩家。
         */
        List<Card> lingHuChong = new ArrayList<>();
        List<Card> jiuMoZhi = new ArrayList<>();
        List<Card> dongfangbubai = new ArrayList<>();

        /**
         e.发牌 依次发出51张牌出去。
         ALL_CARDS = [J♠, 5♠, 2♠, 9♣, 5♦, 4♠, Q♣, 6♥, 8♠, ......
         0   1   2   3   4    5   6   7   8   % 3（轮询的长度）
         */
        for(int i = 0 ; i < ALL_CARDS.size() - 3 ; i++ ){
            // 得到当前这张牌对象
            Card c = ALL_CARDS.get(i);
            // 判断这个牌发给谁
            if(i % 3 == 0 ){
                // 请令狐冲接牌
                lingHuChong.add(c);
            }else if(i % 3 == 1){
                // 请啊鸠接牌
                jiuMoZhi.add(c);
            }else if(i % 3 == 2){
                // 请阿东接牌
                dongfangbubai.add(c);
            }
        }

        /**
         * f.对牌进行排序（拓展,了解）
         */
        sortCards(lingHuChong);
        sortCards(jiuMoZhi);
        sortCards(dongfangbubai);

        /**
         g.看牌
         */
        System.out.println("令狐冲："+lingHuChong);
        System.out.println("鸠摩智："+jiuMoZhi);
        System.out.println("东方不败："+dongfangbubai);
        //System.out.println("底牌："+ALL_CARDS.get(53) +"-"+ALL_CARDS.get(52) + "-" +ALL_CARDS.get(51) );
        // 拓展: 截取集合的最后三张牌到一个新的List集合中去。
        List<Card> lastThreeCards = ALL_CARDS.subList(ALL_CARDS.size() -3 , ALL_CARDS.size());
        System.out.println("底牌："+lastThreeCards);

    }

    /**
     对牌的List集合进行排序（降序排序）
     */
    private static void sortCards(List<Card> cards) {
        // cards = [ 3♦ , 👲,  10♣ , 9♦ , 8♦ , J♠ , ...
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                // o1   Q♦
                // o2   👲
                // 牌的大小就是当前牌在Map集合中的值存储的大小！
                return ALL_CARDS_SIZE.get(o2) - ALL_CARDS_SIZE.get(o1);
            }
        });
    }
}
