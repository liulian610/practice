package com.study.practice.classloader;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by dingdangss on 17/5/12.
 */
public class MainTest {

    public static void main(String[] args) {
        testPrintClassLoader();
        testDiskClassLoader();
        testDecryptClassLoader();
    }

    private static void testPrintClassLoader(){
        System.out.println("sun.boot.class.path:");
        Splitter.on(':').split(System.getProperty("sun.boot.class.path")).forEach(System.out::println);

        System.out.println("java.ext.dirs:");
        Splitter.on(':').split(System.getProperty("java.ext.dirs")).forEach(System.out::println);

        System.out.println("java.class.path:");
        Splitter.on(':').split(System.getProperty("java.class.path")).forEach(System.out::println);

        System.out.println("Current class's ClassLoader: " + MainTest.class.getClassLoader());
        System.out.println("String class's ClassLoader:" + String.class.getClassLoader());
        System.out.println("int class's ClassLoader:" + int.class.getClassLoader());
        System.out.println("Current class's ClassLoader's parent:" + MainTest.class.getClassLoader().getParent());
        System.out.println("Current class's ClassLoader's grand father:" + MainTest.class.getClassLoader().getParent().getParent());
    }

    private static void customClassLoader(ClassLoader loader){
        try{
            Class clz = loader.loadClass("HelloWorld");
            if(clz != null){
                try {
                    Object obj = clz.newInstance();
                    Method method = clz.getDeclaredMethod("sayHello", null);
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    private static void testDiskClassLoader(){
        DiskClassLoader diskLoader = new DiskClassLoader("/Users/dingdangss/Documents");
        customClassLoader(diskLoader);
    }

    private static void testDecryptClassLoader(){
        DecryptClassLoader decryptLoader = new DecryptClassLoader("/Users/dingdangss/Documents");
        customClassLoader(decryptLoader);
    }
}
