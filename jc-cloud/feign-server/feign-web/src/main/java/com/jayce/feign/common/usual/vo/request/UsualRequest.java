package com.jayce.feign.common.usual.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class UsualRequest {
    @ApiModelProperty(value = "message", name = "信息")
    @NotBlank(message = "信息不能为空", groups = {SayHello.class})
    private String message;

    public interface SayHello {
    }
}
