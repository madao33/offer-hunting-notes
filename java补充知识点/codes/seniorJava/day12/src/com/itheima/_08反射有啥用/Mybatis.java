package com.itheima._08反射有啥用;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class Mybatis {
    // 提供一个方法：可以保存一切对象数据的字段和具体值
    public static void save(Object obj) {
        try(
                PrintStream ps =
                        new PrintStream(new FileOutputStream("Day12Demo/src/datas.txt",true));
          ){
            // 解析对象的字段和每个字段的值存储起来！
            // obj = Student/Pig/Teacher....
            // 1.先得到对象的Class文件对象
            Class c = obj.getClass();
            ps.println("========="+c.getSimpleName()+"===========");
            // 2.定位它的全部成员变量
            Field[] fields = c.getDeclaredFields();
            // 3.遍历这些字段并且取值
            for (Field field : fields) {
                // 字段名称
                String name = field.getName();
                // 字段的值
                field.setAccessible(true); // 暴力反射！
                String value = field.get(obj)+"";
                ps.println(name+"="+value);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
