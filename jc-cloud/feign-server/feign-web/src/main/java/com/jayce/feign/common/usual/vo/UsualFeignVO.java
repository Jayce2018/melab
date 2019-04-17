package com.jayce.feign.common.usual.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UsualFeignVO {
    @ApiModelProperty(value = "message", name = "信息")
    @NotBlank(message = "message不能为空", groups = {Message.class})
    private String message;

    @ApiModelProperty(value = "code", name = "编码")
    private String code;

    public interface Message {
    }
}
