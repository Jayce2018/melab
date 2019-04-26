package com.jayce.feign.common.book.vo.extra;

import lombok.Data;

@Data
public class LibraryBookExtra {

    /**
     * 枚举
     */
    public enum StatusEnum {

        //状态枚举
        DELETE(-1, "删除"),

        VALID(1, "有效"),
        ;
        private Integer code;

        private String showValue;

        StatusEnum(Integer code, String showValue) {
            this.code = code;
            this.showValue = showValue;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getShowValue() {
            return showValue;
        }

        public void setShowValue(String showValue) {
            this.showValue = showValue;
        }
    }

    public enum TypeEnum {

        //状态枚举
        TYPE_ENUM_A(1, "历史书"),

        TYPE_ENUM_B(2, "计算机数"),
        ;
        private Integer code;

        private String showValue;

        TypeEnum(Integer code, String showValue) {
            this.code = code;
            this.showValue = showValue;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getShowValue() {
            return showValue;
        }

        public void setShowValue(String showValue) {
            this.showValue = showValue;
        }
    }
}
