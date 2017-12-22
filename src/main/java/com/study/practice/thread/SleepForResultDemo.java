package com.study.practice.thread;

/**
 * Created by dingdangss on 17/5/22.
 */
public class SleepForResultDemo implements Runnable {

    static boolean result = false;

    static void sleepWhile(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        System.out.println("Hello, sleep a while.");
        sleepWhile(2000L);
        result = true;
    }

    public static void main(String[] args) {
        SleepForResultDemo demo = new SleepForResultDemo();
        Thread t = new Thread(demo);
        t.start();
        sleepWhile(3000L);
        System.out.println(result);
    }
}
