package com.exercise.model.emq;


import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Title:Server
 * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
 */
public class Server {

    public static final String HOST = "tcp://127.0.0.1:1883";

    public static final String TOPIC1008600 = "serverToClient/1008600";
    public static final String TOPIC1008601 = "serverToClient/1008601";
    private static final String clientId = "server";

    private MqttClient client;
    private MqttTopic topic;
    private MqttTopic topicA;
    private MqttTopic topicB;
    private String userName = "jayce";
    private String passWord = "jayce";

    private MqttMessage message;

    public Server() throws MqttException {
        // MemoryPersistence设置clientId的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientId, new MemoryPersistence());
        connect();
    }

    public static void main(String[] args) throws MqttException {
        Server server = new Server();

        server.message = new MqttMessage();
        server.message.setQos(2);
        server.message.setRetained(true);
        server.message.setPayload("给客户端1008600推送的信息".getBytes());

        server.publish(server.topicA, server.message);

        server.message = new MqttMessage();
        server.message.setQos(2);
        server.message.setRetained(true);
        server.message.setPayload("给客户端1008601推送的信息".getBytes());
        server.publish(server.topicB, server.message);

        System.out.println(server.message.isRetained() + "------ratained状态");
    }

    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            topicA = client.getTopic(TOPIC1008600);
            topicB = client.getTopic(TOPIC1008601);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(MqttTopic topic, MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }
}
