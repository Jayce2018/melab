package com.jayce.jcemqconsumer.mq.producer.vo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class MessageVO {

    @ApiModelProperty(value = "id", name = "主键")
    private Integer id;

    @ApiModelProperty(value = "content", name = "内容")
    private String content;

    @ApiModelProperty(value = "createTime", name = "创建时间")
    private Date createTime;
}
