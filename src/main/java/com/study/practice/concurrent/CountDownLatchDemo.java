package com.study.practice.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by dingdangss on 17/5/16.
 */
public class CountDownLatchDemo {

    public long timecost(final int times, final Runnable task) throws InterruptedException {
        if(times <= 0) throw new IllegalArgumentException();
        final CountDownLatch startLatch = new CountDownLatch(1);
        final CountDownLatch overLatch = new CountDownLatch(times);
        for(int i = 0; i < times; i++){
            new Thread(() -> {
                try{
                    startLatch.await();
                    task.run();
                } catch(InterruptedException ex){
                    Thread.currentThread().interrupt();
                }finally {
                    overLatch.countDown();
                }
            }).start();
        }
        long start = System.nanoTime();
        startLatch.countDown();
        overLatch.await();
        return System.nanoTime() - start;
    }

}
