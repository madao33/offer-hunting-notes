package com.itheima._26字节流做文件复制;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
    目标：JDK 1.7开始之后释放资源的新方式

    try-with-resources:
         try(
                // 这里只能放置资源对象，用完会自动调用close()关闭
         ){

         }catch(Exception e){
              e.printStackTrace();
         }
    什么是资源？
         资源类一定是实现了Closeable接口，实现这个接口的类就是资源
         有close()方法，try-with-resources会自动调用它的close()关闭资源。
 */
public class CopyDemo02 {
    public static void main(String[] args) {
        try(
                /** （1）创建一个字节输入流管道与源文件接通。 */
                InputStream is  = new FileInputStream("D:\\itcast\\图片资源\\meinv.jpg");
                /** （2）创建一个字节输出流与目标文件接通。*/
                OutputStream os = new FileOutputStream("D:\\itcast\\meimei.jpg");
                /** （5）关闭资源！是自动进行的 */
        ){
            /** （3）创建一个字节数组作为桶*/
            byte[] buffer = new byte[1024];
            /** （4）从字节输入流管道中读取数据，写出到字节输出流管道即可。*/
            int len = 0;
            while((len = is.read(buffer)) != -1){
                // 读取多少就倒出多少
                os.write(buffer, 0 , len);
            }
            System.out.println("复制完成！");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
