package com.study.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by dingdangss on 17/5/11.
 */
public class MainTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:spring-context.xml");
    }
}
