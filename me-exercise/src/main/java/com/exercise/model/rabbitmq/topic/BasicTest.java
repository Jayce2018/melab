package com.exercise.model.rabbitmq.topic;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicTest {
    // rabbitmq的IP地址
    private final String rabbitmq_host = "127.0.0.1";
    // rabbitmq的用户名称
    private final String rabbitmq_user = "jayce";
    // rabbitmq的用户密码
    private final String rabbitmq_pwd = "jayce";
    // 测试线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void topics() throws InterruptedException {

        // 消费者1：绑定 *.orange.* 值
        executorService.submit(() -> {
            String[] bindingKeys = {"*.orange.*"};
            TopicsRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, bindingKeys);
        });

        // 消费者2：绑定  "*.*.rabbit" 和 "lazy.#"值
        executorService.submit(() -> {
            String[] bindingKeys = {"*.*.rabbit", "lazy.#"};
            TopicsRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, bindingKeys);
        });

        Thread.sleep(5 * 100);
        // 生产者1 ： 发送 black，所有的接收端都会收到
        executorService.submit(() -> {
            String routing = "quick.orange.rabbit";
            TopicsSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // 生产者2 ： 发送 green，所有的接收端都不会收到
        executorService.submit(() -> {
            String routing = "lazy.pink.rabbit";
            TopicsSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, routing);
        });

        // sleep 10s
        Thread.sleep(10 * 1000);
    }

}
