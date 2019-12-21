package com.orichalcoss.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanFactory {
    private static Map<String,Object> beanFactory;
    static{
        List<Map<String, Object>> beanInfoList = XmlAnalyse.getBeanDefinition();
        beanFactory = getBeanFactory(beanInfoList);
    }

    private static Map<String,Object> getBeanFactory(List<Map<String, Object>> beanInfoList) {
        Map<String,Object> result = new HashMap<String, Object>();
        for(Map<String, Object> beanInfo : beanInfoList){
            String id = (String)beanInfo.get("id");
            String className = (String)beanInfo.get("class");
            Map<String, String> property = (Map<String, String>)beanInfo.get("property");
            try {
                Class clazz = Class.forName(className);
                Object obj = clazz.newInstance();
                for (String name : property.keySet()) {
                    String value = property.get(name);
                    String getName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1) + "";
                    Class cl = clazz.getDeclaredField(name).getType();
                    Method method = clazz.getDeclaredMethod(getName, cl);
                    if (cl.getName().equals("int") || cl == Integer.class) {
                        method.invoke(obj, Integer.parseInt(value));
                        continue;
                    } else {
                        method.invoke(obj, value);
                    }
                }
                result.put(id, obj);
            }catch(Exception e ){
                e.printStackTrace();
            }
        }
        return result;
    }

    public Object getBean(String beanName){
        Object obj = beanFactory.get(beanName);
        return obj;
    }

}
