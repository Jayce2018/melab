package com.exercise.model.executor.thread;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.concurrent.*;

@ApiModel(value = "DesignThreadPool", description = "自定义线程池")
public class DesignThreadPool {
    @ApiModelProperty(value = "corePoolSize", name = "池容量")
    private int corePoolSize = 10;

    @ApiModelProperty(value = "maximumPoolSize", name = "最大池容量")
    private int maximumPoolSize = 20;

    @ApiModelProperty(value = "keepAliveTime", name = "保活时间")
    private long keepAliveTime = 60;

    @ApiModelProperty(value = "unit", name = "保活单位")
    private TimeUnit unit = TimeUnit.SECONDS;

    @ApiModelProperty(value = "workQueue", name = "阻塞队列")
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(10);

    @ApiModelProperty(value = "executor", name = "线程池执行者")
    private ThreadPoolExecutor executor;

    @ApiModelProperty(value = "designThreadPool", notes = "自定义线程池")
    static DesignThreadPool designThreadPool;

    private DesignThreadPool() {
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @ApiOperation(value = "getInstence",notes = "获取实例")
    public static DesignThreadPool getInstence() {
        synchronized (DesignThreadPool.class) {
            if (null == designThreadPool) {
                designThreadPool=new DesignThreadPool();
                return designThreadPool;
            } else {
                return designThreadPool;
            }
        }
    }

    //runable任务
    public void execute(Runnable runnable){
        executor.execute(runnable);
    }

    //callable任务
    public <V> Future<V> execute(Callable<V> callable){
        return  executor.submit(callable);
    }

    //关闭线程池
    public void shutdown(){
        executor.shutdown();
    }

    //队列剩余
    public boolean isQueueFull(){
        if(workQueue.remainingCapacity()<=0){
            return true;
        }else{
            return false;
        }
    }

    //线程池繁忙
    public boolean isBusy(){
        if(executor.getPoolSize()<=0){
            return true;
        }
        isQueueFull();
        return false;
    }
}
