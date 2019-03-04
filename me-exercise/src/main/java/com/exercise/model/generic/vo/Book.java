package com.exercise.model.generic.vo;

import com.exercise.model.annotation.AddEnum;
import com.exercise.model.utils.enumUtail.EnumMessage;
import lombok.Data;

@Data
public class Book {

    private Long bookId;

    private String name;

    private String author;

    @AddEnum(enumClass = TypeEnum.class, keys ="typeDictionary" )
    private Integer type;

    private String typeDictionary;

    public enum TypeEnum implements EnumMessage {
        //类型枚举
        TYPE_ENUM_ONE(1, "typeA"),
        TYPE_ENUM_TWO(2, "typeB"),
        TYPE_ENUM_THREE(3, "typeC"),
        ;
        public Integer code;
        public String value;

        TypeEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }


        @Override
        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        @Override
        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
