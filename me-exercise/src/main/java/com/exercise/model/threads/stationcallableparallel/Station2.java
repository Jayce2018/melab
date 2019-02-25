package com.exercise.model.threads.stationcallableparallel;

public class Station2 {

    /**
     * 为了保持票数的一致，票数要静态
     */
    private int tick = 80000;

    public Long call() {
        while (1 == 1) {
            if (tick > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + tick + "张票");
                tick--;
            } else {
                System.out.println("票卖完了");
                return System.nanoTime();
            }
        }
    }
}