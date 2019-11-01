package com.exercise.model.rabbitmq.pdf;

public class Properties {
    // rabbitmq的IP地址
    public static final String rabbitmq_host = "10.35.106.153";
    // rabbitmq的IP地址
    public static final int rabbitmq_port = 5672;
    // rabbitmq的用户名称
    public static final String rabbitmq_user = "root";
    // rabbitmq的用户密码
    public static final String rabbitmq_pwd = "dahuacloud";
    // 交换机
    public static final String EXCHANGE_NAME = "test_direct_log";
    // 对列名
    public static final String QUEUE_NAME = "test_queue_log";
}
