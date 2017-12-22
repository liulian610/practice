package com.study.practice.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dingdangss on 17/5/17.
 */
public class CyclicBarrierDemo {

    final CyclicBarrier barrier;
    final int MAX_TASK;

    public CyclicBarrierDemo(int cnt){
        barrier = new CyclicBarrier(cnt, () -> System.out.println("一小批任务执行结束"));
        MAX_TASK = cnt;
    }

    public void doWork(final Runnable work){
        new Thread(){
            public void run(){
                work.run();
                waitForNext();
            }
        }.start();
    }

    public void waitForNext(){
        try{
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException ignored) {
        }
    }

}
