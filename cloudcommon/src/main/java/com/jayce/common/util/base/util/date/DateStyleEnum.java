package com.jayce.common.util.base.util.date;

public enum DateStyleEnum {
    //DateStyleEnum
    DDHHMM(1, "dd:HH:mm"),
    ;

    private Integer code;
    private String value;

    DateStyleEnum(Integer code, String value) {
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

    public static String getEnumValue(Integer code) {
        DateStyleEnum[] flagEna = DateStyleEnum.values();
        for (DateStyleEnum flagEnum : flagEna) {
            if (flagEnum.getCode().equals(code)) {
                return flagEnum.getValue();
            }
        }
        return null;
    }

}
