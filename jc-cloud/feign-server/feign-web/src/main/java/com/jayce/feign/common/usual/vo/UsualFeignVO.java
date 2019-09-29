package com.jayce.feign.common.usual.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


@Data
public class UsualFeignVO {
    @ApiModelProperty(value = "message", name = "信息")
    @NotBlank(message = "message不能为空", groups = {Message.class})
    private String message;

    @ApiModelProperty(value = "code", name = "编码")
    private String code;

    @NotBlank(message = "图书名称不能为空", groups = {FeignBookInsert.class})
    @ApiModelProperty(value = "bookName", name = "图书名称")
    private String bookName;

    @ApiModelProperty(value = "type", name = "图书类型")
    private Integer type;

    @ApiModelProperty(name = "书主键",value = "bookId")
    private Long bookId;

    @ApiModelProperty(name = "状态",value = "status")
    private Integer status;

    public interface Message {
    }

    public interface FeignBookInsert {
    }
}
