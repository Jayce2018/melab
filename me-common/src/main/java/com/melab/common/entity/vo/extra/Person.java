package com.melab.common.entity.vo.extra;

import com.melab.common.annotation.EnumAn;
import lombok.Data;

@Data
public class Person {
    private Long personId;

    private String name;

    @EnumAn(keyEnumClass = Person.GenderEnum.class)
    private Integer gender;

    private String genderString;

    private Long mobile;

    public GenderEnum getByCode(Integer code) {
        GenderEnum[] genderEnums = GenderEnum.values();
        for (GenderEnum genderEnum : genderEnums) {
            if (genderEnum.code.equals(code)) {
                return genderEnum;
            }
        }
        return null;
    }

    public enum GenderEnum {
        GenderEnum_BOY(1, "M"),
        GenderEnum_Girl(2, "F"),
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
