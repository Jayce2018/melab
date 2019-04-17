package com.jayce.jcweb.common.usual.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UsualRequest {
    @ApiModelProperty(value = "message", name = "信息")
    @NotBlank(message = "信息不能为空", groups = {Message.class})
    private String message;

    public interface Message {
    }
}
