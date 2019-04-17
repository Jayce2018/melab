package com.jayce.jcweb.common.usual.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UsualVO {
    @ApiModelProperty(value = "message", name = "信息")
    private String message;
}
