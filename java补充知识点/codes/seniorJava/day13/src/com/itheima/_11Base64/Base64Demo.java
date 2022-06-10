package com.itheima._11Base64;

import java.util.Base64;
import java.util.UUID;

/**
     目标: https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&
         wd=%E9%BB%91%E9%A9%AC%E7%A8%8B%E5%BA%8F%E5%91%98&rsv_pq=adb2aafb0004cea1&rsv_t=bd43rF5NublIew4JRzSmtNoJ2Dtx8lMAuD4NgJOFnkKoA98JL9hZ8DvnSJI&rqlang=cn&rsv_enter=0&rsv_dl=tb

     Base64可以实现编码和解码。
     Java 8 内置了 Base64 编码的编码器和解码器。

     encode: 编码。
     decode: 解码。
 */
public class Base64Demo {
    public static void main(String args[]) {
        try {
            // 1-1.基本编码后结果。普通文本的编码
            String rs1 = Base64.getEncoder().encodeToString("黑马程序员".getBytes());
            System.out.println(rs1); // 6buR6ams56iL5bqP5ZGY

            // 1-2.基本解码后结果。普通文本的解码
            byte[] buffer = Base64.getDecoder().decode(rs1);
            System.out.println(new String(buffer));

            // 2-1.URL编码
            String rs2 = Base64.getUrlEncoder().encodeToString("?loginName=黑马&passWord=123456".getBytes());
            System.out.println(rs2);
            // 2-2 URL解码
            byte[] buffer2 = Base64.getUrlDecoder().decode(rs2);
            System.out.println(new String(buffer2));

            // 3-1 MIME编码
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; ++i) {
                sb.append(UUID.randomUUID().toString());
            }

            String rs3 = Base64.getMimeEncoder().encodeToString(sb.toString().getBytes());
            System.out.println(rs3);

            // 3-2 MIME解码
            byte[] buffer3 = Base64.getMimeDecoder().decode(rs3);
            System.out.println(new String(buffer3));
        }catch(Exception e){
            System.out.println("Error :" + e.getMessage());
        }
    }
}
