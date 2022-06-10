package com.itheima._03Dom4j解析XML的属性;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
    目标：Dom4j解析XML的属性;

    Element元素的API:
        List<Attribute> attributes(): 获取元素的全部属性对象。
        Attribute attribute(String name):根据名称获取某个元素的属性对象。
        String attributeValue(String var1):直接获取某个元素的某个属性名称的值。

    Attribute对象的API:
        String getName():  获取属性名称。
        String getValue(): 获取属性值。

 */
public class Dom4JDemo {
    public static void main(String[] args) throws Exception {
        // 1.创建一个dom4j的解析器对象：代表整个dom4j框架。
        SAXReader saxReader = new SAXReader();

        // 2.第一种方式（简单）：通过解析器对象去加载xml文件数据，成为一个Document文档树对象。
        Document document = saxReader.read(new File("Day13Demo/src/books.xml"));

        // 3.获取根元素对象。
        Element root = document.getRootElement();

        // 4.获取book子元素
        Element bookEle = root.element("book");

        // 5.获取book元素的全部属性对象
        List<Attribute> attributes = bookEle.attributes();
        for (Attribute attribute : attributes) {
            System.out.println(attribute.getName()+"=>"+attribute.getValue());
        }

        // 6.获取Book元素的某个属性对象
        Attribute descAttr = bookEle.attribute("desc");
        System.out.println(descAttr.getName()+"--->"+descAttr.getValue());

        // 7.可以直接获取元素的属性值
        System.out.println(bookEle.attributeValue("id"));
        System.out.println(bookEle.attributeValue("desc"));
    }
}
