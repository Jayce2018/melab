package com.exercise.model.executor.cacheThreadPool;

import io.swagger.annotations.ApiModel;

import java.util.concurrent.*;

@ApiModel(description = "测试",value = "TestCacheTreadPool")
public class TestCacheTreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ExecutorService executorService= Executors.newCachedThreadPool();
        //ExecutorService executorService=Executors.newFixedThreadPool(5);
        //ExecutorService executorService=Executors.newSingleThreadExecutor();
        ExecutorService executorService=Executors.newScheduledThreadPool(5);
        try{
            for (int i = 0; i < 10; i++) {
                Future<String> futures = executorService.submit(new TestCallable());
                System.out.println("result---------------------"+futures.get());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}


class TestRunable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"线程被调用");
    }
}

class TestCallable implements Callable<String>{

    @Override
    public String call() {
        System.out.println(Thread.currentThread().getName()+"线程被调用");
        return Thread.currentThread().getName()+"线程被调用";
    }
}