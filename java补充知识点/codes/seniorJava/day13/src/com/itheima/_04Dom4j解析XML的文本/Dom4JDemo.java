package com.itheima._04Dom4j解析XML的文本;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

/**
    目标：Dom4j解析XML的文本;

    Element:
        String elementText(String name): 可以直接获取当前元素的子元素的文本内容
        String elementTextTrim(String name): 去前后空格,直接获取当前元素的子元素的文本内容
        String getText():直接获取当前元素的文本内容。
        String getTextTrim():去前后空格,直接获取当前元素的文本内容。
 */
public class Dom4JDemo {
    public static void main(String[] args) throws Exception {
        // 1.创建一个dom4j的解析器对象：代表整个dom4j框架。
        SAXReader saxReader = new SAXReader();

        // 2.第一种方式（简单）：通过解析器对象去加载xml文件数据，成为一个Document文档树对象。
        Document document = saxReader.read(new File("Day13Demo/src/books.xml"));

        // 3.获取根元素对象。
        Element root = document.getRootElement();

        // 4.得到第一个子元素book
        Element bookEle = root.element("book");

        // 5.直接拿到当前book元素下的子元素文本值
        System.out.println(bookEle.elementText("name"));
        System.out.println(bookEle.elementTextTrim("name")); // 去前后空格
        System.out.println(bookEle.elementText("author"));
        System.out.println(bookEle.elementTextTrim("author")); // 去前后空格
        System.out.println(bookEle.elementText("sale"));
        System.out.println(bookEle.elementTextTrim("sale")); // 去前后空格

        // 6.先获取到子元素对象，再获取该文本值
        Element bookNameEle = bookEle.element("name");
        System.out.println(bookNameEle.getText());
        System.out.println(bookNameEle.getTextTrim());// 去前后空格
    }
}
