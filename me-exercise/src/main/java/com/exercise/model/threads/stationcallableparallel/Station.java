package com.exercise.model.threads.stationcallableparallel;

import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class Station implements Callable<Long> {

    /**
     * 为了保持票数的一致，票数要静态
     */
    private int tick = 80000;

    /**
     * 创建一个静态钥匙//值是任意的
     */
    static Object ob = "aa";



        /**
         * Computes a result, or throws an exception if unable to do so.
         *
         * @return computed result
         * @throws Exception if unable to compute a result
         */
        @Override
        public Long call(){
            while (1==1) {
                // 这个很重要，必须使用一个锁，
                synchronized (ob) {
                    // 进去的人会把钥匙拿在手上，出来后才把钥匙拿让出来
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
    }