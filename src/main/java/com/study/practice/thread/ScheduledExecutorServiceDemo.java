package com.study.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by dingdangss on 17/5/22.
 */
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("ScheduleAtFixedRate: " + Thread.currentThread().getName() + " -> " + System.currentTimeMillis());
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);

        executorService.scheduleWithFixedDelay(() -> {
            System.out.println("ScheduleWithFixedDelay: " + Thread.currentThread().getName() + " -> " + System.currentTimeMillis());
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.SECONDS);
        Thread.sleep(5000L);
        executorService.shutdown();
    }
}
