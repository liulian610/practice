package com.study.reference;

import java.lang.ref.SoftReference;

/**
 * 软引用.
 * Created by dingdangss on 17/5/23.
 */
public class SoftReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        MyObject myObject = new MyObject();
        myObject.setId(1000L);
        myObject.setName("name");
        System.out.println(myObject);

        SoftReference<MyObject> softReference = new SoftReference<>(myObject);
        myObject = null;
        System.out.println(myObject);
        MyObject anotherObject = softReference.get();
        System.out.println(anotherObject);
        System.out.println(anotherObject.getId().toString());
        System.out.println(anotherObject.getName());
    }
}
