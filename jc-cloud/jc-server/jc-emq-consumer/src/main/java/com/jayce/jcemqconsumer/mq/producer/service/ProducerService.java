package com.jayce.jcemqconsumer.mq.producer.service;

import com.alibaba.fastjson.JSONObject;
import com.jayce.common.util.base.exception.device.DeviceException;
import com.jayce.jcemqconsumer.mq.producer.vo.dto.ProducerVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProducerService {
    @Value("${emq.produceTopic}")
    private String deviceServerTopic;

    @Value("${emq.produceQosLevel}")
    private int produceQosLevel;

    @Autowired
    private MqttClient emqDeviceProducer;

    @ApiOperation(value = "sendSyncMessage", notes = "发送消息")
    public ProducerVO sendSyncMessage(String lmqClientId, Object o) {
        //对象转json
        JSONObject messageJSON = (JSONObject) JSONObject.toJSON(o);
        MqttMessage message = new MqttMessage(messageJSON.toString().getBytes());
        message.setQos(produceQosLevel);
        ProducerVO sendResult = new ProducerVO();
        try {
            String topic = deviceServerTopic + "/" + lmqClientId;
            emqDeviceProducer.publish(topic, message);
            sendResult.setTopic(topic);
            sendResult.setMessageId(message.getId() + "");
            log.info("EMQ 发送消息成功,消息主键[" + message.getId() + "]，消息内容[" + message.toString() + "]");
        } catch (MqttException e) {
            log.error("EMQ 发送消息失败", e);
            throw new DeviceException("EMQ 发送消息失败", e);
        }
        return sendResult;
    }
}
