package com.orichalcoss;

import com.orichalcoss.model.Computer;
import com.orichalcoss.model.Student;
import com.orichalcoss.utils.BeanFactory;
import com.orichalcoss.utils.XmlAnalyse;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) throws Exception {
        BeanFactory beanFactory = new BeanFactory();
        Student student = (Student)beanFactory.getBean("student");
        Computer computer = (Computer)beanFactory.getBean("computer");
        System.out.println(student);
        System.out.println("_______________");
        System.out.println(computer);
    }
}
