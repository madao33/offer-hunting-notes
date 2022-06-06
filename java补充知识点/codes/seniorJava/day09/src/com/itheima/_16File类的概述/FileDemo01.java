package com.itheima._16File类的概述;

import java.io.File;

/**
    目标：File类的概述和API

    File类：代表操作系统的文件对象。
    File类：是用来操作操作系统的文件对象的，删除文件，获取文件信息，创建文件（文件夹）...
    广义来说操作系统认为文件包含（文件和文件夹）

    File类的创建文件对象的API:
         包：java.io.File
        （1）构造器：
            -- public File(String pathname):根据路径获取文件对象
            -- public File(String parent , String child):根据父路径和文件名称获取文件对象！
            -- public File(File parent , String child)

    File类创建文件对象的格式:
        a.File f = new File("绝对路径/相对路径");
            绝对路径：从磁盘的的盘符一路走到目的位置的路径。
                -- 绝对路径依赖具体的环境，一旦脱离环境，代码可能出错！！
                -- 一般是定位某个操作系统中的某个文件对象。
            相对路径：不带盘符的。（重点）
                -- 默认是直接相对到工程目录下寻找文件的。
                -- 相对路径只能用于寻找工程下的文件。
                -- 能用相对路径就应该尽量使用，可以跨平台！

        b.File f = new File("文件对象/文件夹对象");
            广义来说：文件是包含文件和文件夹的。
    小结：
        创建文件对象可以用绝对路径也可以用相对路径。
        相对路径只能用于寻找工程下的文件。
        文件对象可以表示文件也可以表示文件夹！
 */
public class FileDemo01 {
    public static void main(String[] args) {
        // 1.创建文件对象：使用绝对路径
        // 文件路径分隔符：
        //      -- a.使用正斜杠： /
        //      -- b.使用反斜杠： \\
        //      -- c.使用分隔符API:File.separator
        //File f1 = new File("D:/itcast/图片资源/beautiful.jpg");
        //File f1 = new File("D:"+File.separator+"itcast"+File.separator+"图片资源"+File.separator+"beautiful.jpg");
        File f1 = new File("D:\\itcast\\图片资源\\beautiful.jpg");
        System.out.println(f1.length()); // 获取文件的大小，字节大小

        // 2.创建文件对象：使用相对路径
        File f2 = new File("Day09Demo/src/dlei01.txt");
        System.out.println(f2.length());

        // 3.创建文件对象：代表文件夹。
        File f3 = new File("D:\\itcast\\图片资源");
        System.out.println(f3.exists());// 判断路径是否存在！！
    }
}
