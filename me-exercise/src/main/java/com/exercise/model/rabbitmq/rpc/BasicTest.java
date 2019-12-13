package com.exercise.model.rabbitmq.rpc;

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

    public void rpc() throws InterruptedException {

        // rpc服务端
        executorService.submit(() -> {
            RpcServer.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd);
        });

        // rpc客户端
        executorService.submit(() -> {
            RpcClient.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, "消息队列远程连接，rpc test");
        });

        // sleep 10s
        Thread.sleep(10 * 1000);
    }
}
