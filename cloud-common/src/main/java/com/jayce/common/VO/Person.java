package com.jayce.common.VO;

import com.jayce.common.annotation.FieldDict;
import lombok.Data;

import java.util.List;

@Data
public class Person {
    private Long personId;

    private String name;

    @FieldDict(dictionaryClass = Person.GenderEnum.class)
    private Integer gender;

    private String genderString;

    private String genderDict;

    private Long mobile;

    private List<Person> list;

    //字段枚举
    public enum GenderEnum {
        GenderEnum_BOY(1, "男"),
        GenderEnum_Girl(2, "女"),
        GenderEnum_Null(3, "未知"),
        ;
        public Integer code;
        public String value;

        GenderEnum(Integer code, String value) {
            this.code = code;
            this.value = value;
        }


        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
