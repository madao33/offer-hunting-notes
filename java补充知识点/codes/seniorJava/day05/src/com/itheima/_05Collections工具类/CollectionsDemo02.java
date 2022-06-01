package com.itheima._05Collections工具类;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
    目标：引用数据类型的排序。

    字符串按照首字符的编号升序排序！

    自定义类型的比较方法API:
         - public static <T> void sort(List<T> list):
               将集合中元素按照默认规则排序。
               对于自定义的引用类型的排序人家根本不知道怎么排，直接报错！
               如果希望自定义的引用类型排序不报错，可以给类提供比较规则:Comparable。

         - public static <T> void sort(List<T> list，Comparator<? super T> c):
                将集合中元素按照指定规则排序,自带比较器
                注意：如果类有比较规则，而这里有比较器，优先使用比较器。

 */
public class CollectionsDemo02 {
    public static void main(String[] args) {
        // 自定义类型如何排序！
        List<Orange> oranges = new ArrayList<>();
        Orange o1 = new Orange("红橘子",654.0 ,"贼便宜~");
        Orange o2 = new Orange("黄橘子",454.0 ,"贼便宜~");
        Orange o3 = new Orange("黄橘子",454.0 ,"贼便宜~");
        Orange o4 = new Orange("青橘子",456.0 ,"贼便宜~");
        Collections.addAll(oranges,o1,o2,o3,o4);
        Collections.sort(oranges); // 排序，按照类实现的比较规则进行排序！！
        System.out.println(oranges);


        List<Orange> oranges1 = new ArrayList<>();
        Orange o11 = new Orange("红橘子",654.0 ,"贼便宜~");
        Orange o22 = new Orange("黄橘子",454.0 ,"贼便宜~");
        Orange o33 = new Orange("黄橘子",454.0 ,"贼便宜~");
        Orange o44 = new Orange("青橘子",456.0 ,"贼便宜~");
        Collections.addAll(oranges1,o11,o22,o33,o44);
        // 优先用方法自带的比较器对象Comparator而不会用类的比较规则！！
        Collections.sort(oranges1, new Comparator<Orange>() {
            @Override
            public int compare(Orange o1, Orange o2) {
                if(o1.getWeight() > o2.getWeight()) return -1;
                if(o1.getWeight() < o2.getWeight()) return 1;
                return 0;
            }
        });
        System.out.println(oranges1);
    }
}
