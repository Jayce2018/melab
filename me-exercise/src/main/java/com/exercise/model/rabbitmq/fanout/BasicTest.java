package com.exercise.model.rabbitmq.fanout;

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
    void publishSubscribe() throws InterruptedException {
        // 接收端
        int recNum = 2;
        while (recNum-- > 0) {
            final int recId = recNum;
            executorService.submit(() -> {
                Subscribe.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, recId);
            });
        }
        Thread.sleep(5 * 100);
        // 发送端
        int sendNum = 2;
        while (sendNum-- > 0) {
            executorService.submit(() -> {
                Publish.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
            });
        }

        // sleep 10s
        Thread.sleep(10 * 1000);
    }
}
