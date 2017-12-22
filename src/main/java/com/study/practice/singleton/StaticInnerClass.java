package com.study.practice.singleton;

/**
 * 用静态内部类的方式实现单例模式.
 *
 * @author dingdangss.
 * @since 2017-12-08.
 */
public class StaticInnerClass {

    private static class InnerClass {
        private static StaticInnerClass singleton = new StaticInnerClass();
    }

    private StaticInnerClass() {

    }

    public static StaticInnerClass getInstance() {
        return InnerClass.singleton;
    }

    public static void main(String[] args) {
        System.out.println(StaticInnerClass.getInstance());
        System.out.println(StaticInnerClass.getInstance());
        System.out.println(StaticInnerClass.getInstance());
    }
}
