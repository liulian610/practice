package com.study.thread;

import java.lang.reflect.Field;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by dingdangss on 17/5/22.
 */
public class ExecutorServiceDemo {

    static void log(String msg){
        System.out.println(System.currentTimeMillis() + " -> " + msg);
    }

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1));
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i = 0; i < 10; i++){
            final int index = i;
            pool.submit(() -> {
                log("run task: " + index + " -> " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log("run over: " + index + " -> " + Thread.currentThread().getName());
            });
        }
        log("before sleep");
        Thread.sleep(4000L);
        log("before shutdown()");
        pool.shutdown();
        log("after shutdown(), pool.isTerminated = " + pool.isTerminated());
        pool.awaitTermination(1000L, TimeUnit.SECONDS);
        log("now, pool.isTerminated = " + pool.isTerminated());
    }
}
