package com.study.threadlocal;

/**
 * Created by dingdangss on 17/5/24.
 */
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        /*ThreadLocal tl = new MyThreadLocal();
        tl.set(new My50MB());
        tl = null;
        System.out.println("Full GC");
        System.gc();*/

        new Thread(() -> {
           ThreadLocal tl = new MyThreadLocal();
            tl.set(new My50MB());
            tl = null;
            System.out.println("Full GC");
            System.gc();
        }).start();

        System.gc();
        Thread.sleep(1000);
        System.gc();
        Thread.sleep(1000);
        System.gc();
        Thread.sleep(1000);
    }

    public static class MyThreadLocal extends ThreadLocal {
        private byte[] a = new byte[1024 * 1024 * 1];

        @Override
        public void finalize(){
            System.out.println("My threadlocal 1 MB finalized.");
        }
    }

    public static class My50MB {
        private byte[] a = new byte[1024 * 1024 * 50];

        @Override
        public void finalize(){
            System.out.println("My 50 MB finalized.");
        }
    }
}
