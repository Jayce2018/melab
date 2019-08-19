package com.exercise.model.executor.thread;

public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            DesignThreadPool.getInstence().execute(new TestRunable());

            DesignThreadPool.getInstence().execute(new TestCallable());

        }
    }
}
