package com.exercise.model.threads.stationcallableparallel;

import java.util.ArrayList;
import java.util.concurrent.*;

public class StationMain {
    static int NUM = 5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime1 = System.nanoTime();
        ExecutorService exec1 = Executors.newCachedThreadPool();//工头
        ArrayList<Future<Long>> results1 = new ArrayList<Future<Long>>();//
        for(int i = 0 ; i < 2 ;i++){
            results1.add(exec1.submit(new Station()));//submit返回一个Future，代表了即将要返回的结果
        }

        System.out.println(results1);
        // 获取所有并发任务的运行结果
        Long endTime1 = null;
        for (Future f : results1) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            endTime1= (Long) f.get();
        }



        /*long startTime2 = System.nanoTime();
        ExecutorService exec2 = Executors.newCachedThreadPool();//工头
        ArrayList<Future<Long>> results2 = new ArrayList<Future<Long>>();//

        results2.add(exec2.submit(new Station()));//submit返回一个Future，代表了即将要返回的结果
        System.out.println(results2);
        // 获取所有并发任务的运行结果
        Long endTime2 = null;
        for (Future f : results2) {
            // 从Future对象上获取任务的返回值，并输出到控制台
            endTime2= (Long) f.get();
        }
        System.out.println("并行结果:"+(endTime2-startTime2));
        System.out.println("两条线并行时间"+(endTime2-startTime1));*/

        long startTime3 = System.nanoTime();
        Station2 station2=new Station2();
        station2.call();
        Station2 station3=new Station2();
        station3.call();
        long endTime3 = System.nanoTime();
        System.out.println("一条线单行时间"+(endTime3-startTime3));
        System.out.println("两条线并行时间"+(endTime1-startTime1));

    }
}
