package com.exercise.model.rabbitmq.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Subscribe {
    private static final String EXCHANGE_NAME = "test_fanout_logs";

    public static void execute(String host, String userName, String password, int id) {
        // 配置连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        // 需要在管理后台增加一个hry帐号
        factory.setUsername(userName);
        factory.setPassword(password);
        try {
            // 建立TCP连接
            Connection connection = factory.newConnection();
            // 在TCP连接的基础上创建通道
            Channel channel = connection.createChannel();
            // 声明一个fanout交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            // 声明一个临时队列
            String queueName = channel.queueDeclare().getQueue();
            // 将临时队列绑定到交换机上
            /**
             * 第一个参数queue：要绑定的队列
             * 第二个参数exchange：要绑定的交换机
             * 第三个参数routingKey：绑定使用的路由键
             * */
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            System.out.println(" [Subscribe-" + id + "] Waiting for messages.");

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(" [Subscribe-" + id + "] Received '" + message + "'");
                }
            };
            // 接收消费消息
            channel.basicConsume(queueName, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}