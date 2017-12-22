package com.study.practice.reference;

import java.util.WeakHashMap;

/**
 * 弱引用.
 * Created by dingdangss on 17/5/23.
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {
        int size = 1000;
        Key[] keys = new Key[size];
        WeakHashMap<Key, Value> map = new WeakHashMap<>();
        for(int i = 0; i < size; i++){
            Key k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            if(i % 3 == 0){
                keys[i] = k;
            }
            map.put(k, v);
        }
        System.gc();
        System.out.println(map.size());
    }
}

class Element{
    private String ident;
    public Element(String id){
        ident = id;
    }
    public String toString(){
        return ident;
    }
    public int hashCode(){
        return ident.hashCode();
    }
    public boolean equals(Object obj){
        return obj instanceof Element && ident.equals(((Element)obj).ident);
    }
    protected void finalize(){
        System.out.println("Finalizing:" + getClass().getSimpleName() + ":" + ident);
    }
}

class Key extends Element{
    public Key(String id){
        super(id);
    }
}

class Value extends Element{
    public Value(String id){
        super(id);
    }
}