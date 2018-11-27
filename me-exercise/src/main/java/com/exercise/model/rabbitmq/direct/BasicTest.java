package com.exercise.model.rabbitmq.direct;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicTest {
    // rabbitmq的IP地址
    private final String rabbitmq_host = "localhost";
    // rabbitmq的用户名称
    private final String rabbitmq_user = "jayce";
    // rabbitmq的用户密码
    private final String rabbitmq_pwd = "jayce";
    // 测试线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    //”单个绑定”的场景
    public void routing_1() throws InterruptedException {
        // 接收端 1：绑定 orange 值
        executorService.submit(() -> {
            String[] colours = {"orange"};
            RoutingRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, colours);
        });
        // 接收端 2：绑定 black、green 值
        executorService.submit(() -> {
            String[] colours = {"black", "green"};
            RoutingRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, colours);
        });

        Thread.sleep(5 * 100);
        // 发送端 ： 发送 black，只有接收端1收到
        executorService.submit(() -> {
            String routing = "orange";
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // 发送端 ： 发送 green、black，只有接收端2收到
        /*executorService.submit(() -> {
            String routing = "green";
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });*/

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

    @Test
    //”多个绑定”的场景
    public void routing_2() throws InterruptedException {
        // 接收端：同时创建两个接收端，同时绑定black
        int recNum = 2;
        while (recNum-- > 0) {
            // 接收端：绑定 black 值
            executorService.submit(() -> {
                String[] colours = {"black"};
                RoutingRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, colours);
            });
        }

        // 接收端：同时创建两个接收端，同时绑定black
        /*int recNum2 = 2;
        while(recNum2-- > 0) {
            // 接收端：绑定 black 值
            executorService.submit(() -> {
                String[] colours = {"green"};
                RoutingRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, colours);
            });
        }*/

        Thread.sleep(5 * 100);
        // 发送端1 ： 发送 black，所有的接收端都会收到
        executorService.submit(() -> {
            String routing = "black";
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // 发送端2 ： 发送 green，所有的接收端都不会收到
        executorService.submit(() -> {
            String routing = "green";
            RoutingSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // sleep 10s
        Thread.sleep(10 * 1000);
    }
}
