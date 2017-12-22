package com.study.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dingdangss on 17/5/17.
 */
public class ReentrantLockDemo {

    private int value;

    private Lock lock = new ReentrantLock();

    public ReentrantLockDemo(){
        super();
    }

    public ReentrantLockDemo(int value){
        this.value = value;
    }

    public final int get(){
        lock.lock();
        try{
            return value;
        } finally {
            lock.unlock();
        }
    }

    public final void set(int newValue){
        lock.lock();
        try{
            value = newValue;
        } finally {
            lock.unlock();
        }
    }

    public final int incrementAndGet() {
        lock.lock();
        try {
            return ++value;
        } finally {
            lock.unlock();
        }
    }
}
