package com.exercise.model.rabbitmq.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publish {
    private static final String EXCHANGE_NAME = "logs";

    public static void execute(String host, String userName, String password) {
        // 配置连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        // 需要在管理后台增加一个hry帐号
        factory.setUsername(userName);
        factory.setPassword(password);
        Connection connection = null;
        Channel channel = null;
        try {
            // 建立TCP连接
            connection = factory.newConnection();
            // 在TCP连接的基础上创建通道
            channel = connection.createChannel();
            // 声明一个fanout交换机
            // channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            /**
             * 第一个参数exchange：交换机的名称
             * 第二个参数type：交换机的类型
             * 第三个参数durable：是否持久化，如果true，则当前RabbitMQ重启的时候，它依旧存在
             * 第四个参数autoDelete：当没有生成者/消费者使用此交换机时，此交换机会被自动删除。
             * 第五个参数arguments：其它的扩展属性
             * */
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, false, false, null);
            String message = "Publish-" + "发送消息，扇形交换机测试！" + System.currentTimeMillis();
            // 发送消息
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            System.out.println(" [Publish] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 空值判断，为了代码简洁略
                assert channel != null;
                channel.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
