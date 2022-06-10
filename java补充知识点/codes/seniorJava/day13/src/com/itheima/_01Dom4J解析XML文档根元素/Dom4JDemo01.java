package com.itheima._01Dom4J解析XML文档根元素;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.InputStream;

/**
    目标：dom4j: 获取Document对象和根元素

    dom4j属于第三方技术，必须导入该框架！！

    dom4j安装步骤：
        a.去dom4j官网下载dom4j的框架：都是一些jar包。
        b.把dom4j的核心jar包导入到当前项目中去。
        c.在项目中创建一个文件夹：lib
        d.将dom4j-2.1.1.jar文件复制到 lib 文件夹
        e.在jar文件上点右键，选择 Add as Library -> 点击OK
        f.在类中导包使用

    Java提供了Class下的一个方法：
         public InputStream getResourceAsStream(String path)
            -- 用于加载文件成为一个字节输入流返回！！

    Document文档：
         Element getRootElement()：获取根元素。

    小结：
        先导入dom4j框架。
        创建一个dom4j的解析对象：SAXReader
        通过解析对象把xml文件解析成Document文档对象。
        从Document文档对象中获取我们想要的xml信息。
 */
public class Dom4JDemo01 {
    public static void main(String[] args) throws Exception {
        // 需求：解析books.xml文件成为一个Document文档树对象，得到根元素对象。
        // 1.创建一个dom4j的解析器对象：代表整个dom4j框架。
        SAXReader saxReader = new SAXReader();

        // 2.第一种方式（简单）：通过解析器对象去加载xml文件数据，成为一个Document文档树对象。
        //Document document = saxReader.read(new File("Day13Demo/src/books.xml"));

        // 3.第二种方式（代码多点）先把xml文件读成一个字节输入流
        // 这里的“/”是直接去src类路径下寻找文件。
        InputStream is = Dom4JDemo01.class.getResourceAsStream("/books.xml");
        Document document = saxReader.read(is);

        System.out.println(document);

        // 4.从document文档树对象中提取根元素对象
        Element root = document.getRootElement();
        System.out.println(root.getName());
    }
}











