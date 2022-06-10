package com.itheima._06XPath检索XML中的信息;

import com.itheima._01Dom4J解析XML文档根元素.Dom4JDemo01;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
    目标：XPath检索XML中的信息啊。

    引入：
        Dom4J可以用于解析整个XML的数据。
        但是如果要检索XML中的某些信息，建议使用XPath.
    XPath使用步骤：
        1.导入dom4j框架。（XPath依赖于Dom4j技术,必须先倒入dom4j框架！）
        2.导入XPath独有的框架包。jaxen-1.1.2.jar
    XPath常用API:
        List<Node> selectNodes(String var1):检索出一批节点集合。
        Node selectSingleNode(String var1)：检索出一个节点返回。
    XPath提供的四种检索数据的写法：
        1.绝对路径。
        2.相对路径。
        3.全文搜索。
        4.属性查找。

    小结：
         1.绝对路径： /根元素/子元素/子元素。
         2.相对路径： ./子元素/子元素。 (.代表了当前元素)
         3.全文搜索：
                //元素  在全文找这个元素
                //元素1/元素2  在全文找元素1下面的一级元素2
                //元素1//元素2  在全文找元素1下面的全部元素2
         4.属性查找。
                //@属性名称  在全文检索属性对象。
                //元素[@属性名称]  在全文检索包含该属性的元素对象。
                //元素[@属性名称=值]  在全文检索包含该属性的元素且属性值为该值的元素对象。
 */
public class XPathDemo {

    //  1.绝对路径： /根元素/子元素/子元素。
    @Test
    public void path01() throws Exception {
        // 1.创建一个dom4j的解析器对象：代表整个dom4j框架。
        SAXReader saxReader = new SAXReader();

        // 2.第二种方式（代码多点）先把xml文件读成一个字节输入流
        // 这里的“/”是直接去src类路径下寻找文件。
        InputStream is = Dom4JDemo01.class.getResourceAsStream("/Contact.xml");
        Document document = saxReader.read(is);
        // 3.使用绝对路径定位全部的name名称
        List<Node> nameNodes = document.selectNodes("/contactList/contact/name");
        for (Node nameNode : nameNodes) {
            System.out.println(nameNode.getText());
        }
    }

    // 2.相对路径： ./子元素/子元素。 (.代表了当前元素)
    @Test
    public void path02() throws Exception {
        // 1.创建一个dom4j的解析器对象：代表整个dom4j框架。
        SAXReader saxReader = new SAXReader();

        // 2.第二种方式（代码多点）先把xml文件读成一个字节输入流
        // 这里的“/”是直接去src类路径下寻找文件。
        InputStream is = Dom4JDemo01.class.getResourceAsStream("/Contact.xml");
        Document document = saxReader.read(is);

        // 3.得到根元素对象
        Element root = document.getRootElement();

        // 4.从根元素开始检索
        // .代表当前根元素对象路径！直接找其下的contact下的name
        List<Node> nameNodes = root.selectNodes("./contact/name");
        for (Node nameNode : nameNodes) {
            System.out.println(nameNode.getText());
        }
    }

    // 3.全文搜索：
    //                "//元素"  在全文找这个元素
    //                "//元素1/元素2"  在全文找元素1下面的一级元素2
    //                "//元素1//元素2"  在全文找元素1下面的全部元素2
    @Test
    public void path03() throws Exception {
        // 1.创建一个dom4j的解析器对象：代表整个dom4j框架。
        SAXReader saxReader = new SAXReader();

        // 2.第二种方式（代码多点）先把xml文件读成一个字节输入流
        // 这里的“/”是直接去src类路径下寻找文件。
        InputStream is = Dom4JDemo01.class.getResourceAsStream("/Contact.xml");
        Document document = saxReader.read(is);

        // 3.//name是指在整个xml文件中检索name节点！
        List<Node> nameNodes = document.selectNodes("//name");
        for (Node nameNode : nameNodes) {
            System.out.println(nameNode.getText());
        }
        System.out.println("-----------------");
        // 4.在全文中检索所有contact下的直接name节点
        List<Node> nameNodes1 = document.selectNodes("//contact/name");
        for (Node nameNode : nameNodes1) {
            System.out.println(nameNode.getText());
        }
        System.out.println("-----------------");
        // 5.在全文中检索所有contact下的所有name节点
        List<Node> nameNodes2 = document.selectNodes("//contact//name");
        for (Node nameNode : nameNodes2) {
            System.out.println(nameNode.getText());
        }
    }

    //   4.属性查找。
    //         //@属性名称  在全文检索属性对象。
    //          //元素[@属性名称]  在全文检索包含该属性的元素对象。
    //          //元素[@属性名称=值]  在全文检索包含该属性的元素且属性值为该值的元素对象。
    @Test
    public void path04() throws Exception {
        // 1.创建一个dom4j的解析器对象：代表整个dom4j框架。
        SAXReader saxReader = new SAXReader();

        // 2.第二种方式（代码多点）先把xml文件读成一个字节输入流
        // 这里的“/”是直接去src类路径下寻找文件。
        InputStream is = Dom4JDemo01.class.getResourceAsStream("/Contact.xml");
        Document document = saxReader.read(is);

        // 3.检索全部属性对象
        List<Node> attributs = document.selectNodes("//@id");
        for (Node attribut : attributs) {
            Attribute attr = (Attribute) attribut;
            System.out.println(attr.getName() + "--->"+attr.getValue());
        }
        System.out.println("---------------");
        // 4.//元素[@属性名称]  在全文检索包含该属性的元素对象
        List<Node> nodeEles = document.selectNodes("//contact[@id]");
        for (Node nodeEle : nodeEles) {
            System.out.println(nodeEle.getName());
        }

        System.out.println("---------------");
        // 5. //元素[@属性名称=值]  在全文检索包含该属性的元素且属性值为该值的元素对象。
        Node nodeEle = document.selectSingleNode("//contact[@id=2]");
        Element ele = (Element) nodeEle;
        System.out.println(ele.elementTextTrim("name"));
    }
}
