package com.jayce.jcemqconsumer.mq.producer.vo.http;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProducerResponse {
    @ApiModelProperty(value = "code", name = "编号")
    private String code;
}
