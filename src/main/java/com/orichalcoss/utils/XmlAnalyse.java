package com.orichalcoss.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlAnalyse {
    public static List<Map<String, Object>> getBeanDefinition() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read("./src/main/resources/spring-config.xml");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element rootEle = document.getRootElement();
        List<Element> rchilds = rootEle.elements();
        for (Element e : rchilds) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id",e.attribute("id").getValue());
            map.put("class",e.attribute("class").getValue());
            List<Element> echilds = e.elements();
            Map<String, Object> mapSon = new HashMap<String, Object>();
            for (Element e2 : echilds) {
                mapSon.put(e2.attribute("name").getValue(),e2.attribute("value").getValue());
            }
            map.put("property",mapSon);
            mapSon = new HashMap<String, Object>();
            result.add(map);
        }
        return result;
    }
}