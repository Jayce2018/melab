package com.exercise.model.rabbitmq.head;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
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
    public void header() throws InterruptedException {

        // 消费者1：绑定 format=pdf,type=report
        executorService.submit(() -> {
            Map<String, Object> headers = new HashMap();
            headers.put("format", "pdf");
            headers.put("type", "report");
            headers.put("x-match", "all");
            HeaderRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, headers);
        });

        // 消费者2：绑定  format=pdf,type=log
        executorService.submit(() -> {
            Map<String, Object> headers = new HashMap();
            headers.put("format", "pdf");
            headers.put("type", "log");
            headers.put("x-match", "any");
            HeaderRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, headers);
        });

        // 消费者3：绑定  format=zip,type=report
        executorService.submit(() -> {
            Map<String, Object> headers = new HashMap();
            headers.put("format", "zip");
            headers.put("type", "report");
            headers.put("x-match", "all");
            //   headers.put("x-match","any");
            HeaderRecv.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, headers);
        });

        Thread.sleep(2 * 1000);
        System.out.println("=============消息1===================");
        // 生产者1 ： format=pdf,type=reprot,x-match=all
        executorService.submit(() -> {
            Map<String, Object> headers = new HashMap();
            headers.put("format", "pdf");
            headers.put("type", "report");
            //     headers.put("x-match","all");
            HeaderSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, headers);
        });

        Thread.sleep(5 * 100);
        System.out.println("=============消息2===================");
        // 生产者2 ： format=pdf,x-match=any
        executorService.submit(() -> {
            Map<String, Object> headers = new HashMap();
            headers.put("format", "pdf");
            //     headers.put("x-match","any");
            HeaderSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, headers);
        });

        Thread.sleep(5 * 100);
        System.out.println("=============消息3===================");
        // 生产者1 ： format=zip,type=log,x-match=all
        executorService.submit(() -> {
            Map<String, Object> headers = new HashMap();
            headers.put("format", "zip");
            headers.put("type", "log");
            //      headers.put("x-match","all");
            HeaderSend.execute(rabbitmq_host, rabbitmq_user, rabbitmq_pwd, headers);
        });

        // sleep 10s
        Thread.sleep(10 * 1000);
    }
}
