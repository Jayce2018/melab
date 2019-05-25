package com.jayce.jcemqconsumer.mq.consumer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;

@Configuration
@Slf4j
public class EMQConsumerConfiguration {

    @Value("${emq.brokerUrl}")
    private String brokerUrl;

    @Value("${emq.userName}")
    private String userName;

    @Value("${emq.password}")
    private String password;

    @Value("${emq.consumeFlag}")
    private String consumeFlag;

    @Value("${emq.produceFlag}")
    private String produceFlag;

    @Value("${emq.consumeTopic}")
    private String consumeTopic;

    @Value("${emq.connectedTopic}")
    private String connectedTopic;

    @Value("${emq.disconnectedTopic}")
    private String disconnectedTopic;

    @Value("${emq.systemTopic}")
    private String systemTopic;

    @Value("${emq.consumeQosLevel}")
    private int consumeQosLevel;

    @Value("${emq.cleanSession}")
    private boolean cleanSession;

    @Value("${emq.serverConsumerClientId}")
    private String serverConsumerClientId;

    @Value("${emq.serverProducerClientId}")
    private String serverProducerClientId;

    @Value("${emq.heartSecond}")
    private int heartSecond;

    @Bean(name = "emqDeviceConsumer")
    public MqttClient emqDeviceConsumer() throws Exception {

        final MemoryPersistence memoryPersistence = new MemoryPersistence();
        final MqttClient mqttClient = new MqttClient(brokerUrl, serverConsumerClientId, memoryPersistence);
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
                try {
                    final String topicFilter[] = {consumeTopic, connectedTopic, disconnectedTopic};
                    final int[] qos = {consumeQosLevel, consumeQosLevel, consumeQosLevel};
                    mqttClient.subscribe(topicFilter, qos);
                } catch (MqttException e) {
                    log.error("订阅主题失败", e);
                }
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
                //设备上线
                if(topic.contains("/connected")){
                    connectedMessage(mqttMessage);
                }
                //设备下线
                if(topic.contains("/disconnected")){
                    disconnectedMessage(mqttMessage);
                }
                //消费消息
               if(topic.contains(consumeFlag)){
                    dealMessage(mqttMessage);
                }

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

    @ApiOperation(value = "dealMessage", notes = "处理消息")
    public void dealMessage(MqttMessage message) {
        String messageId = message.getId() + "";
        log.info("接收消息:[messageId->" + messageId + "]" + message.toString());
        try {
            byte[] messageBodyArray = message.getPayload();
            String jsonString = new String(messageBodyArray, Charset.forName("utf-8"));
            log.info("接受消息:[messageId->" + messageId + "]\n" + jsonString);
            //转换成JSON对象
            try {
                JSONObject messageJSONObject = JSONObject.parseObject(jsonString);
                log.info("处理消息，报文参数："+messageJSONObject);
            }catch (JSONException e){
                log.info("接受消息:[messageId->" + messageId + "]\n" + jsonString);
            }
        } catch (Exception e) {
            log.error("【异常】接受消息:[messageId->" + messageId + "]" + message.toString(), e);
        }
    }

    @ApiOperation(value = "connected", notes = "设备上线")
    public void connectedMessage(MqttMessage message) {
        String messageId = message.getId() + "";
        log.info("设备上线接收消息:[messageId->" + messageId + "]" + message.toString());
        byte[] messageBodyArray = message.getPayload();
        String jsonString = new String(messageBodyArray, Charset.forName("utf-8"));
        log.info("设备上线接受消息:[messageId->" + messageId + "]\n" + jsonString);
        //转换成JSON对象
        try {
            JSONObject messageJSONObject = JSONObject.parseObject(jsonString);
            String clientId = messageJSONObject.getString("clientid");
            log.info("设备上线，设备号："+clientId);
        }catch (JSONException e){
            log.info("设备上线接受消息:[messageId->" + messageId + "]\n" + jsonString);
        }
    }

    @ApiOperation(value = "disconnected", notes = "设备下线")
    public void disconnectedMessage(MqttMessage message) {
        String messageId = message.getId() + "";
        log.info("设备下线接收消息:[messageId->" + messageId + "]" + message.toString());
        byte[] messageBodyArray = message.getPayload();
        String jsonString = new String(messageBodyArray, Charset.forName("utf-8"));
        log.info("设备下线接受消息:[messageId->" + messageId + "]\n" + jsonString);
        //转换成JSON对象
        try {
            JSONObject messageJSONObject = JSONObject.parseObject(jsonString);
            String clientId = messageJSONObject.getString("clientid");
            log.info("设备下线，设备号："+clientId);
        }catch (JSONException e){
            log.info("设备下线接受消息:[messageId->" + messageId + "]\n" + jsonString);
        }

    }
}
