package com.jayce.jcemqconsumer.mq.producer.vo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProducerVO {

    @ApiModelProperty(value = "topic", name = "主题")
    private String topic;

    @ApiModelProperty(value = "messageId", name = "消息主键")
    private String messageId;
}
