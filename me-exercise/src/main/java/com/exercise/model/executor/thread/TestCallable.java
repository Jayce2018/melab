package com.exercise.model.executor.thread;

import java.util.concurrent.Callable;


public class TestCallable implements Callable<String>{
    @Override
    public String call() {
        System.out.println(Thread.currentThread().getName()+"线程被调用");
        return Thread.currentThread().getName()+"线程被调用";
    }
}