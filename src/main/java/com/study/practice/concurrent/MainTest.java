package com.study.concurrent;

/**
 * Created by dingdangss on 17/5/16.
 */
public class MainTest {

    private static int staticValue = 0;

    public static void main(String[] args) throws InterruptedException {
//        testReentrantLock();
//        testCondition();
//        testCountDownLatch();
        testCyclicBarrier();
    }

    private static void testCyclicBarrier(){
        // 总共100个任务,分批处理,每批处理10个任务。前一批完成才能处理下一批的任务
        final int count = 10;
        CyclicBarrierDemo demo = new CyclicBarrierDemo(count);
        for(int i = 0; i < 100; i++){
            demo.doWork(() -> {
                try{
                    System.out.println("thread: " + Thread.currentThread().getId());
                }catch (Exception ignored){
                }
            });
        }
    }

    private static void testCountDownLatch() throws InterruptedException {
        long time = new CountDownLatchDemo().timecost(10, () ->
                System.out.println(Thread.currentThread().toString())
        );
        System.out.println("cost time: " + time);
    }

    private static void testCondition() throws InterruptedException {
        ConditionDemo<String> demo = new ConditionDemo<>();
        demo.put("aaa");
        demo.put("bbb");
        System.out.println("demo size: " + demo.size());
        System.out.println("demo take: " + demo.take());
        System.out.println("demo size: " + demo.size());
    }

    private static void testReentrantLock() throws InterruptedException {
        final int max = 10;
        final int loopCount = 100000;
        long costTime = 0;
        for(int m = 0; m < max; m++){
            long start = System.nanoTime();
            final ReentrantLockDemo value = new ReentrantLockDemo(0);
            Thread[] ts = new Thread[max];
            for(int i = 0; i < max; i++){
                ts[i] = new Thread(){
                    public void run(){
                        for(int i = 0; i < loopCount; i++){
                            value.incrementAndGet();
                        }
                    }
                };
            }
            for(Thread t : ts){
                t.start();
            }
            for(Thread t : ts){
                t.join();
            }
            long end = System.nanoTime();
            costTime += end - start;
        }
        System.out.println("cost time1: " + costTime);
        costTime = 0;

        final Object lock = new Object();
        for (int m = 0; m < max; m++) {
            staticValue = 0;
            long start = System.nanoTime();
            Thread[] ts = new Thread[max];
            for(int i = 0; i < max; i++) {
                ts[i] = new Thread() {
                    public void run() {
                        for (int i = 0; i < loopCount; i++) {
                            synchronized(lock) {
                                ++staticValue;
                            }
                        }
                    }
                };
            }
            for(Thread t : ts) {
                t.start();
            }
            for(Thread t : ts) {
                t.join();
            }
            long end = System.nanoTime();
            costTime += (end - start);
        }
        System.out.println("cost time2: " + (costTime));
    }
}
