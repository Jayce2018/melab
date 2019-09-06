package com.exercise.model.design.responsibilitychain;

public enum TypeEnum {

    TYPE_ENUM_FATHER(1,"父亲"),
    TYPE_ENUM_HUSBAND(2,"丈夫"),
    TYPE_ENUM_SON(3,"儿子"),
    ;

    private Integer code;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    TypeEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public static String getValue(Integer code){
        String value="";
        for (TypeEnum item:TypeEnum.values()) {
            if(item.getCode().equals(code)){
                value=item.getValue();
            }
        }
        return value;
    }
}
