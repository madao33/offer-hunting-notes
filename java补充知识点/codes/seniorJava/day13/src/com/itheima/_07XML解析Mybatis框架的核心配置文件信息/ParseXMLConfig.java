package com.itheima._07XML解析Mybatis框架的核心配置文件信息;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXWriter;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.FileOutputStream;
import java.util.List;
import java.util.regex.Pattern;

/**

 */
public class ParseXMLConfig {
    @Test
    public void parseXML() throws Exception {
        // 1.创建一个解析器对象
        SAXReader saxReader = new SAXReader();
        // 2.加载类路径下的xml文件成为一个document文档对象。
        Document document = saxReader.read(ParseXMLConfig.class.getResourceAsStream("/sqlMapConfig.xml"));
        // 3.得到根元素对象
        Element root = document.getRootElement();
        // 4.获取子元素environments
        Element environments = root.element("environments");
        // 5.获取子元素environment
        Element environment = environments.element("environment");
        // 6.获取子元素dataSource
        Element dataSource = environment.element("dataSource");
        // 7.获取 dataSource下的全部子元素
        List<Element>  properties = dataSource.elements();
        // 8.遍历他们
        for (Element property : properties) {
            System.out.println(property.attributeValue("name")
                    +"==>"+property.attributeValue("value"));
        }



    }
}
