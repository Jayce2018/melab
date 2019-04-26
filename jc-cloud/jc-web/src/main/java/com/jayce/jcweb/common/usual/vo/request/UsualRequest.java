package com.jayce.jcweb.common.usual.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;


@Data
public class UsualRequest {
    @ApiModelProperty(value = "message", name = "信息")
    @NotBlank(message = "信息不能为空", groups = {Message.class})
    private String message;

    @NotNull(message = "类型不能为空", groups = {InsertBook.class})
    @ApiModelProperty(value = "type", name = "类型")
    private Integer type;

    @ApiModelProperty(value = "bookName", name = "书名")
    @NotBlank(message = "书名不能为空", groups = {InsertBook.class})
    private String bookName;

    public interface Message {
    }

    public interface InsertBook {
    }
}
