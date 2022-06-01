package com.itheima._03List系列集合的使用;

import java.util.LinkedList;
import java.util.List;

/**
     目标：LinkedList集合。

     Collection集合的体系:
                Collection<E>(接口)
     /                                       \
     Set<E>(接口)                             List<E>(接口)
     /                                   /                    \               \
     HashSet<E>(实现类)                   LinkedList<E>(实现类) Vector(实现类)  ArrayList<E>(实现类)
     /
     LinkedHashSet<E>(实现类)

     Collection集合体系的特点:
         Set系列集合： 添加的元素，是无序，不重复，无索引的。
             -- HashSet：添加的元素，是无序，不重复，无索引的。
             -- LinkedHashSet：添加的元素，是有序，不重复，无索引的。
         List系列集合：添加的元素，是有序，可重复，有索引的。
             -- LinkedList： 添加的元素，是有序，可重复，有索引的。
             -- ArrayList： 添加的元素，是有序，可重复，有索引的。

     LinkedList也是List的实现类：底层是基于链表的，增删比较快，查询慢！！
     LinkedList是支持双链表，定位前后的元素是非常快的，增删首尾的元素也是最快的
     所以LinkedList除了拥有List集合的全部功能还多了很多操作首尾元素的特殊功能：
         - public void addFirst(E e):将指定元素插入此列表的开头。
         - public void addLast(E e):将指定元素添加到此列表的结尾。
         - public E getFirst():返回此列表的第一个元素。
         - public E getLast():返回此列表的最后一个元素。
         - public E removeFirst():移除并返回此列表的第一个元素。
         - public E removeLast():移除并返回此列表的最后一个元素。
         - public E pop():从此列表所表示的堆栈处弹出一个元素。
         - public void push(E e):将元素推入此列表所表示的堆栈。

    小结：
         LinkedList是支持双链表，定位前后的元素是非常快的，增删首尾的元素也是最快的。
         所以提供了很多操作首尾元素的特殊API可以做栈和队列的实现。

         如果查询多而增删少用ArrayList集合。(用的最多的)
         如果查询少而增删首尾较多用LinkedList集合。
 */
public class ListDemo03 {
    public static void main(String[] args) {
        // 1.用LinkedList做一个队列:先进先出，后进后出。
        LinkedList<String> queue = new LinkedList<>();
        // 入队
        queue.addLast("1号");
        queue.addLast("2号");
        queue.addLast("3号");
        queue.addLast("4号");
        System.out.println(queue); // [1号, 2号, 3号, 4号]
        // 出队
        System.out.println(queue.removeFirst());
        System.out.println(queue.removeFirst());
        System.out.println(queue);

        // 做一个栈
        LinkedList<String> stack = new LinkedList<>();
        // 压栈
        stack.push("第1颗子弹");
        stack.push("第2颗子弹");
        stack.push("第3颗子弹");
        stack.push("第4颗子弹");
        System.out.println(stack); // [第4颗子弹, 第3颗子弹, 第2颗子弹, 第1颗子弹]
        // 弹栈
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);


    }
}
