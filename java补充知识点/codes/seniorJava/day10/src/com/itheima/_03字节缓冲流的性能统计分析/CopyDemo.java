package com.itheima._03字节缓冲流的性能统计分析;

import java.io.*;

/**
    目标：利用字节流的复制统计各种写法形式下缓冲流的性能执行情况。

    复制流：
        （1）使用低级的字节流按照一个一个字节的形式复制文件。
        （2）使用低级的字节流按照一个一个字节数组的形式复制文件。
        （3）使用高级的缓冲字节流按照一个一个字节的形式复制文件。
        （4）使用高级的缓冲字节流按照一个一个字节数组的形式复制文件。

    源文件：D:\itcast\班级\Java就业128期\day08\15.并发包ConcurrentHashMap.avi
    目标文件：D:\itcast\班级\new新视频.avi

    小结：
        高级的字节缓冲流按照一个一个字节数组的形式复制性能极高，建议以后使用！
 */
public class CopyDemo {
    public static final String SRC_FILE = "D:\\itcast\\班级\\Java就业128期\\day08\\15.并发包ConcurrentHashMap.avi";
    public static final String DEST_FIlE = "D:\\itcast\\班级\\";
    public static void main(String[] args) {
        // copy01(); // 低级流一个一个字节复制，速度太慢，简直让人无法忍受，直接淘汰，禁止使用！
        copy02(); // 低级的字节流按照一个一个字节数组的形式复制 ,读取较慢。5.264s
        copy03(); // 高级的缓冲字节流按照一个一个字节的形式复制 ,读取较慢。4.032s
        copy04(); // 高级的字节缓冲流按照一个一个字节数组的形式复制,速度极快。建议使用 0.71s
    }

    /** （1）使用低级的字节流按照一个一个字节的形式复制文件。*/
    public static void copy01(){
        long startTimer = System.currentTimeMillis();
        try(
                // 1.创建一个低级的字节输入流与源文件接通
                InputStream is = new FileInputStream(SRC_FILE);
                // 2.创建一个敌机的字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream(DEST_FIlE+"01.avi");
                ){
                // 3.定义一个整型变量存储读取的字节。
                int ch ;
                while((ch = is.read())!=-1){
                    os.write(ch);
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        long endTimer = System.currentTimeMillis();
        System.out.println("低级的字节流按照一个一个字节的形式复制文件耗时："+(endTimer-startTimer)/1000.0);
    }

    /** （2）使用低级的字节流按照一个一个字节数组的形式复制文件。*/
    public static void copy02(){
        long startTimer = System.currentTimeMillis();
        try(
                // 1.创建一个低级的字节输入流与源文件接通
                InputStream is = new FileInputStream(SRC_FILE);
                // 2.创建一个敌机的字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream(DEST_FIlE+"02.avi");
        ){
            // 3.定义一个字节数组存储字节
            byte[] buffer = new byte[1024];
            // 定义一个变量存储每次读取的字节数量。
            int len ;
            while((len = is.read(buffer))!=-1){
                os.write(buffer,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        long endTimer = System.currentTimeMillis();
        System.out.println("低级的字节流按照一个一个字节数组的形式复制文件耗时："+(endTimer-startTimer)/1000.0);
    }

    /** （3）使用高级的缓冲字节流按照一个一个字节的形式复制文件。*/
    public static void copy03(){
        long startTimer = System.currentTimeMillis();
        try(
                // 1.创建一个低级的字节输入流与源文件接通
                InputStream is = new FileInputStream(SRC_FILE);
                BufferedInputStream bis = new BufferedInputStream(is);
                // 2.创建一个敌机的字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream(DEST_FIlE+"03.avi");
                BufferedOutputStream bos = new BufferedOutputStream(os);
        ){
            // 3.定义一个整型变量存储读取的字节。
            int ch ;
            while((ch = bis.read())!=-1){
                bos.write(ch);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        long endTimer = System.currentTimeMillis();
        System.out.println("高级的缓冲字节流按照一个一个字节的形式复制文件耗时："+(endTimer-startTimer)/1000.0);
    }

    /** （4）使用高级的缓冲字节流按照一个一个字节数组的形式复制文件。*/
    public static void copy04(){
        long startTimer = System.currentTimeMillis();
        try(
                // 1.创建一个低级的字节输入流与源文件接通
                InputStream is = new FileInputStream(SRC_FILE);
                BufferedInputStream bis = new BufferedInputStream(is);
                // 2.创建一个敌机的字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream(DEST_FIlE+"04.avi");
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
        long endTimer = System.currentTimeMillis();
        System.out.println("高级的字节缓冲流按照一个一个字节数组的形式复制文件耗时："+(endTimer-startTimer)/1000.0);
    }
}
