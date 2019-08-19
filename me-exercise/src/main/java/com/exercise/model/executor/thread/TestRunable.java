package com.exercise.model.executor.thread;

public class TestRunable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程被调用");
    }
}