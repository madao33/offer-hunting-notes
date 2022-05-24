package com.itheima._08内部类_实例内部类;

/**
    目标：内部类_实例内部类（成员内部类）(了解语法为主)

    什么是实例内部类：
        无static修饰的内部类，属于外部类的每个对象的，跟着对象一起加载的。

    实例内部类的成分特点：
        实例内部类中不能定义静态成员，其他都可以定义。
        可以定义常量。

    实例内部类的访问格式：
        外部类名称.内部类名称。

    创建对象的格式：
        外部类名称.内部类名称 对象名称 = new 外部类构造器.new 内部构造器;

    拓展：
        实例内部类中是否可以直接访问外部类的静态成员？可以的，外部类的静态成员可以被共享访问！
        实例内部类中是否可以访问外部类的实例成员？可以的，实例内部类属于外部类对象，可以直接访问当前外部类对象的实例成员！
    小结：
        实例内部类属于外部类对象，需要用外部类对象一起加载，
        实例内部类可以访问外部类的全部成员！
 */
public class InnerClass {
    public static void main(String[] args) {
        // 实例内部类属于外部类对象。实例内部类的宿主是外部类对象！！
        Outter.Inner in = new Outter().new Inner();
        in.show();
    }
}
// 外部类
class Outter{
    public static int age = 1;
    private double salary;

    // 实例内部类：无static修饰，属于外部类的对象
    public class Inner{
        private String name ;

        public static final String schoolName = "黑马";
        // 不能在实例内部类中定义静态成员！！！
//      public static String schoolName = "黑马";
//      public static void test(){
//
//      }

        // 实例方法
        public void show(){
            System.out.println(name+"名称！");
            System.out.println(age);
            System.out.println(salary);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}