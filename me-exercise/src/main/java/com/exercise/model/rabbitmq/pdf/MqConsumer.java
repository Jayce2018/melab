package com.exercise.model.rabbitmq.pdf;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MqConsumer {
    public static void execute(String routingKey) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername(Properties.rabbitmq_user);
            factory.setPassword(Properties.rabbitmq_pwd);
            connection = factory.newConnection(new Address[]{new Address(Properties.rabbitmq_host, Properties.rabbitmq_port)});
            channel = connection.createChannel();
            channel.basicQos(3);
            channel.queueBind(Properties.QUEUE_NAME,Properties.EXCHANGE_NAME,routingKey);
            Channel finalChannel = channel;

            Consumer consume = new DefaultConsumer(finalChannel) {
                @Override
                public void handleDelivery(String consumerTag,
                                           Envelope envelope,
                                           AMQP.BasicProperties properties,
                                           byte[] body)
                        throws IOException {
                    System.out.println("consumerTag:"+consumerTag);
                    System.out.println("envelope:"+JSONObject.toJSON(envelope));
                    System.out.println("properties:"+JSONObject.toJSON(properties));
                    System.out.println("<<<<message：" + new String(body));
                    finalChannel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            channel.basicConsume(Properties.QUEUE_NAME,consume);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
