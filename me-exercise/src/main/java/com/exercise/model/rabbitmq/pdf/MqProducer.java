package com.exercise.model.rabbitmq.pdf;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

public class MqProducer {
    static ConnectionFactory factory = null;
    static Connection connection = null;
    static Channel channel = null;

    static {
        factory = new ConnectionFactory();
        factory.setUsername(Properties.rabbitmq_user);
        factory.setPassword(Properties.rabbitmq_pwd);
        try {
            connection = factory.newConnection(new Address[]{new Address(Properties.rabbitmq_host, Properties.rabbitmq_port)});
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        //信道
        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute(String routingKey) throws IOException, TimeoutException {
        //交换机(持久化、非自动删除)
        channel.exchangeDeclare(Properties.EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true, false, null);
        //队列（持久化、非排他、非自动删除）
        channel.queueDeclare(Properties.QUEUE_NAME, true, false, false, null);
        //队列绑定
        channel.queueBind(Properties.QUEUE_NAME, Properties.EXCHANGE_NAME, routingKey);

        String message = "hello world,时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        channel.basicPublish(Properties.EXCHANGE_NAME, routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println("----");
        System.out.println(">>>>message:"+message);
    }
}
