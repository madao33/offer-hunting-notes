package com.itheima._09装饰设计模式;
// 装饰模式！提升原始功能！！！
public class BufferedInputStrem extends InputStream {
    private InputStream is ;
    public BufferedInputStrem(InputStream is){
        this.is = is;
    }
    @Override
    public void read() {
        System.out.println("开启高效缓冲读取~");
        is.read();
    }

    @Override
    public void close() {
        is.close();
    }
}
