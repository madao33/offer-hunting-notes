package com.itheima._20File目录的遍历;

import java.io.File;
import java.text.SimpleDateFormat;

/**
     目标：File针对目录的遍历
     - public String[] list()：
             获取当前目录下所有的"一级文件名称"到一个字符串数组中去返回。
     - public File[] listFiles()(常用)：
             获取当前目录下所有的"一级文件对象"到一个文件对象数组中去返回（重点）

 */
public class FileDemo {
    public static void main(String[] args) {
        File dir = new File("day09/src/com/itheima");
        // a.获取当前目录对象下的全部一级文件名称到一个字符串数组返回。
        String[] names = dir.list();
        for (String name : names) {
            System.out.println(name);
        }
        // b.获取当前目录对象下的全部一级文件对象到一个File类型的数组返回。
        File[] files = dir.listFiles();
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }

        // ---------拓展------------
        File f1 = new File("C:\\Users\\Administrator\\Documents\\codes\\notes\\java-notes\\java补充知识点\\codes\\seniorJava\\day09\\src\\com\\itheima\\_20File目录的遍历\\FileDemo.java");
        long time = f1.lastModified(); // 最后修改时间！
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(time));
    }
}
