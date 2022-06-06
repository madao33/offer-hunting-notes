package com.itheima._00编程思维题目;

import java.io.*;

/**
    目标：复制文件夹。（面试题，拓展）

    源文件：D:\itcast\e。
    目标文件：D:\itcast\ebak
 */
public class CopyDirDemo02 {
    // 定义两个文件夹
    public static void main(String[] args) {
        copyDir(new File("D:\\itcast\\e") , new File("D:\\itcast\\ebak"));
    }

    /**
     * 复制文件夹
     * @param srcDir
     * @param destDir
     */
    public static void copyDir(File srcDir , File destDir){
        // 1.判断是否存在原路径，是否是文件夹。
        if(srcDir.exists() && srcDir.isDirectory()){
            // 2.创建复制的目标文件夹
            destDir.mkdirs();
            // 3.提取源文件夹的一级文件对象。
            File[] files = srcDir.listFiles();
            // 4.判断是否存在一级文件对象。
            if(files!=null && files.length > 0){
                // 5.遍历一级文件对象
                for (File file : files) {
                    // 6.判断是否是文件
                    if(file.isFile()){
                        // 直接把当前文件复制过去到当前目标路径下。
                        copyFile(file , new File(destDir  , file.getName()));
                    }else{
                        // file是文件夹了，作为新的原文件夹，
                        // 目标文件夹：上一个文件夹destDir + 新文件夹名称
                        copyDir(file , new File(destDir , file.getName()));
                    }
                }
            }
        }
    }

    private static void copyFile(File srcFile, File destFile) {
        try(
                // 1.创建一个低级的字节输入流与源文件接通
                InputStream is = new FileInputStream(srcFile);
                BufferedInputStream bis = new BufferedInputStream(is);
                // 2.创建一个敌机的字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream(destFile);
                BufferedOutputStream bos = new BufferedOutputStream(os);
        ){
            // 3.定义一个字节数组存储字节
            byte[] buffer = new byte[1024];
            // 定义一个变量存储每次读取的字节数量。
            int len ;
            while((len = bis.read(buffer))!=-1){
                bos.write(buffer,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}




