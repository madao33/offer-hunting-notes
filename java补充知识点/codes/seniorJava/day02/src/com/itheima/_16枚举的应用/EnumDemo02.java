package com.itheima._16枚举的应用;

/**
    目标：枚举的作用：是为了做信息的标志和信息的分类。

    常量做信息分类和信息标志：
        虽然可以实现可读性，但是入参不受限制！！！

    Java建议做信息标志和信息分类应该使用枚举实现：最优雅的方式。
        可以实现可读性，而且入参受限制，不能乱输入！！！

    小结：
        枚举的作用：是为了做信息的标志和信息的分类。
 */
enum Oritation{
    UP , DOWN , LEFT , RIGHT ;
}
public class EnumDemo02 {
    public static void main(String[] args) {
        move(Oritation.DOWN); // 方法入参只能输入枚举的4个类型！
    }

    // 提供一个方法控制玛丽的方向。
    // 上下左右
    public static void move(Oritation o){
        switch (o){
            case UP:
                System.out.println("让🐎往👆蹦~~~~");
                break;
            case DOWN:
                System.out.println("让🐎往👇蹦~~~~");
                break;
            case LEFT:
                System.out.println("让🐎往👈蹦~~~~");
                break;
            case RIGHT:
                System.out.println("让🐎往👉蹦~~~~");
                break;
        }
    }

//    public static final int UP = 0;
//    public static final int DOWN = 1;
//    public static final int LEFT = 2;
//    public static final int RIGHT = 3;
//    public static void main(String[] args) {
//        move(RIGHT);
//    }
//
//    // 提供一个方法控制玛丽的方向。
//    // 上下左右  0 1 2 3
//    public static void move(int oritatin){
//        switch (oritatin){
//            case 0:
//                System.out.println("让🐎往👆蹦~~~~");
//                break;
//            case 1:
//                System.out.println("让🐎往👇蹦~~~~");
//                break;
//            case 2:
//                System.out.println("让🐎往👈蹦~~~~");
//                break;
//            case 3:
//                System.out.println("让🐎往👉蹦~~~~");
//                break;
//            default:
//                System.out.println("蒙蔽了~~~~");
//        }
//    }
}
