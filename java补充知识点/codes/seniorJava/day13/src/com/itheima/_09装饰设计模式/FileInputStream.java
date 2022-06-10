package com.itheima._09装饰设计模式;

public class FileInputStream extends InputStream {
    @Override
    public void read() {
        System.out.println("读取数据~~~");
    }

    @Override
    public void close() {
        System.out.println("关闭流~~~");
    }
}
