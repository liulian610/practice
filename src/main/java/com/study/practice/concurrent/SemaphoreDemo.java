package com.study.practice.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于信号量Semaphore的对象池实现.
 * Created by dingdangss on 17/5/17.
 */
public class SemaphoreDemo<T> {

    public interface ObjFactory<T>{
        T makeObject();
    }
    class Node{
        T obj;
        Node next;
    }

    final int capacity;
    final ObjFactory<T> factory;
    final Lock lock = new ReentrantLock();
    final Semaphore semaphore;
    private Node head, tail;

    public SemaphoreDemo(int capacity, ObjFactory<T> objFactory){
        this.capacity = capacity;
        this.factory = objFactory;
        this.semaphore = new Semaphore(this.capacity);
        this.head = null;
        this.tail = null;
    }

    public T getObj() throws InterruptedException {
        semaphore.acquire();
        return getNextObj();
    }

    public T getNextObj(){
        lock.lock();
        try{
            if(head == null){
                return factory.makeObject();
            }else {
                Node ret = head;
                head = head.next;
                if(head == null){
                    tail = null;
                }
                ret.next = null;// GC
                return ret.obj;
            }
        } finally {
            lock.unlock();
        }
    }

    public void returnObjToPool(T t){
        lock.lock();
        try{
            Node node = new Node();
            node.obj = t;
            if(tail == null){
                head = tail = node;
            }else{
                tail.next = node;
                tail = node;
            }
        } finally {
            lock.unlock();
        }
    }

    public void returnObj(T t){
        returnObjToPool(t);
        semaphore.release();
    }
}
