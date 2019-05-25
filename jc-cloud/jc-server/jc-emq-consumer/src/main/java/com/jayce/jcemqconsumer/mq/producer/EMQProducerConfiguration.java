package com.jayce.jcemqconsumer.mq.producer;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class EMQProducerConfiguration {

    @Value("${emq.brokerUrl}")
    private String brokerUrl;

    @Value("${emq.consumeTopic}")
    private String consumeTopic;

    @Value("${emq.consumeQosLevel}")
    private int consumeQosLevel;

    @Value("${emq.cleanSession}")
    private boolean cleanSession;

    @Value("${emq.serverProducerClientId}")
    private String serverProducerClientId;

    @Value("${emq.heartSecond}")
    private int heartSecond;

    @Value("${emq.userName}")
    private String userName;

    @Value("${emq.password}")
    private String password;

    @Bean(name = "emqDeviceProducer")
    public MqttClient emqDeviceProducer() throws Exception {

        final MemoryPersistence memoryPersistence = new MemoryPersistence();
        final MqttClient mqttClient = new MqttClient(brokerUrl, serverProducerClientId, memoryPersistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setUserName(userName);
        connOpts.setPassword(password.toCharArray());
        connOpts.setCleanSession(cleanSession);
        connOpts.setKeepAliveInterval(heartSecond);
        connOpts.setAutomaticReconnect(true);
        mqttClient.setCallback(new MqttCallbackExtended() {
            @Override
            @ApiOperation(value = "connectComplete", notes = "连接成功")
            public void connectComplete(boolean reconnect, String serverURI) {
                log.info("EMQ服务端连接成功");
            }

            @Override
            @ApiOperation(value = "connectionLost", notes = "失去连接")
            public void connectionLost(Throwable throwable) {
                log.error("EMQ服务端订阅丢失连接", throwable);
                log.info("EMQ服务端订阅丢失连接，开始重新连接...");
                try {
                    mqttClient.connect(connOpts);
                    log.info("EMQ服务端订阅丢失连接，重新连接成功");
                } catch (MqttException e) {
                    log.info("EMQ服务端订阅丢失连接，重新连接失败", e);
                }
            }

            @Override
            @ApiOperation(value = "messageArrived", notes = "连接接收消息")
            public void messageArrived(String topic, MqttMessage mqttMessage) {
                log.info("EMQ 服务端接收来自主体[" + topic + "] 的消息, 内容 " + new String(mqttMessage.getPayload()));
            }

            @Override
            @ApiOperation(value = "deliveryComplete", notes = "接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌时调用")
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                log.info("接收到已经发布的 QoS 1 或 QoS 2 消息的传递令牌");
            }
        });
        mqttClient.connect(connOpts);
        return mqttClient;
    }

}
