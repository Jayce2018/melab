package com.jayce.jcemqconsumer.mq.producer.vo.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProducerRequest {
    @ApiModelProperty(value = "message", name = "消息内容")
    private String message;
}
