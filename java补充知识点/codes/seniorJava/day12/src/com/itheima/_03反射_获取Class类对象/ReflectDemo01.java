package com.itheima._03反射_获取Class类对象;
/**
    目标：反射获取Class类对象。

    引入：
        反射是通过先得到编译以后的Class类对象：字节码文件。
        然后才可以得到类中的全部成分，进行一些功能设计。

    反射为一个类的全部成分都设计了一个类型来代表这个对象：
        Class : 字节码文件的类型
        Constructor : 构造器的类型
        Field : 成员变量的类型
        Method : 方法的类型

    反射技术的第一步永远是先得到Class类对象:有三种方式获取
        （1） 类名.class
        （2） 通过类的对象.getClass()方法
        （3） Class.forName("类的全限名")
                -- public static Class<?> forName(String className)

    Class类下的方法：
         String getSimpleName(); 获得类名字符串：类名
         String getName();  获得类全名：包名+类名
         T newInstance() ;  创建Class对象关联类的对象,其实底层也是调用无参数构造器，已经被淘汰。
    小结：
        Class类对象的获取有三种方式：
            1.类名.class。
            2.通过类的对象.getClass()方法。
            3.Class.forName("类的全限名")。
        Class类的方法：
            String getSimpleName(); 获得类名字符串：类名
            String getName();  获得类全名：包名+类名
 */
public class ReflectDemo01 {
    public static void main(String[] args) throws Exception {
        // 反射的第一步永远是先得到类的Class文件对象: 字节码文件。
        // 1.类名.class
        Class c1 = Student.class;
        System.out.println(c1);

        // 2.对象.getClass()
        Student swk = new Student();
        Class c2 = swk.getClass();
        System.out.println(c2);

        // 3.Class.forName("类的全限名")
        // 直接去加载该类的class文件。
        Class c3 = Class.forName("com.itheima._03反射_获取Class类对象.Student");
        System.out.println(c3);

        System.out.println(c1.getSimpleName()); // 获取类名本身（简名）
        System.out.println(c1.getName()); // 获取类的全限名
        // Student s1 = (Student) c1.newInstance(); // 调用无参数构造器得到对象，被淘汰了！

    }
}
