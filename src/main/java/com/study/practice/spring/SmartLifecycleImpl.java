package com.study.spring;

import org.springframework.context.SmartLifecycle;

/**
 * Created by dingdangss on 17/5/11.
 */
public class SmartLifecycleImpl implements SmartLifecycle {
    public boolean isAutoStartup() {
        System.out.println("SmartLifecycleImpl:isAutoStartup");
        return true;
    }

    public void stop(Runnable callback) {
        System.out.println("SmartLifecycleImpl:stop(Runnable)");
    }

    public void start() {
        System.out.println("SmartLifecycleImpl:start");
    }

    public void stop() {
        System.out.println("SmartLifecycleImpl:stop");
    }

    public boolean isRunning() {
        System.out.println("SmartLifecycleImpl:isRunning");
        return false;
    }

    public int getPhase() {
        return 0;
    }
}
