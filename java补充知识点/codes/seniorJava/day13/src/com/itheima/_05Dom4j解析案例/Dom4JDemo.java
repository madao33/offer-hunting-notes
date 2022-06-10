package com.itheima._05Dom4j解析案例;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
   目标：Dom4j解析XML文件:Contacts.xml成为一个Java的对象（集合对象）

   Contacts.xml 解析成===> List<Contact>

    分析：
        1.定义一个联系人类封装联系人数据。
        2.解析成List集合。
 */
public class Dom4JDemo {
    public static void main(String[] args) throws Exception {
        // 1.创建一个dom4j的解析器对象：代表整个dom4j框架。
        SAXReader saxReader = new SAXReader();

        // 2.第一种方式（简单）：通过解析器对象去加载xml文件数据，成为一个Document文档树对象。
        Document document = saxReader.read(new File("Day13Demo/src/Contacts.xml"));

        // 3.获取根元素对象。
        Element root = document.getRootElement();

        // 4.获取根元素下的全部子元素
        List<Element> sonElements = root.elements();

        // 5.遍历子元素 封装成List集合对象
        List<Contact> contactList = new ArrayList<>();
        if(sonElements != null && sonElements.size() > 0) {
            for (Element sonElement : sonElements) {
                Contact contact = new Contact();
                contact.setId(Integer.valueOf(sonElement.attributeValue("id")));
                contact.setVip(Boolean.valueOf(sonElement.attributeValue("vip")));
                contact.setName(sonElement.elementText("name"));
                contact.setSex(sonElement.elementText("gender").charAt(0));
                contact.setEmail(sonElement.elementText("email"));
                contactList.add(contact);
            }
        }
        System.out.println(contactList);
    }
}
