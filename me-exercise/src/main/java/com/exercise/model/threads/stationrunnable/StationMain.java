package com.exercise.model.threads.stationrunnable;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class StationMain {
    static int NUM = 5;

    public static void main(String[] args) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("station1-%d").build();
        ExecutorService executor = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < NUM; i++) {
            Station station = new Station();
            executor.execute(station);
        }
        executor.shutdown();

        ThreadFactory namedThreadFactory2 = new ThreadFactoryBuilder()
                .setNameFormat("station2-%d").build();
        ExecutorService executor2 = new ThreadPoolExecutor(5, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory2, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < NUM; i++) {
            Station station = new Station();
            executor2.execute(station);
        }
        executor2.shutdown();
    }
}
