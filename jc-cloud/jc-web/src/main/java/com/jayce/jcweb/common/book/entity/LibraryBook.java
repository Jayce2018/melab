package com.jayce.jcweb.common.book.entity;

import com.jayce.common.annotation.FieldDict;
import com.jayce.jcweb.common.book.vo.extra.LibraryBookExtra;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Table(name = "library_book")
public class LibraryBook extends LibraryBookExtra {
    /**
     * 图书主键
     */
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long bookId;

    /**
     * 图书名称
     */
    @Column(name = "book_name")
    private String bookName;

    /**
     * 图书类型
     */
    private Integer type;

    /**
     * 删除状态（1、有效 -1、删除）
     */
    @FieldDict(dictionaryClass=StatusEnum.class)
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    //非持久化字段
    @Transient
    private String statusString;


    //类间枚举
    public enum StatusEnum {
        STATUS_ENUM_Valid(1, "有效"),
        STATUS_ENUM_Invalid(-1, "无效"),
        ;

        private Integer code;
        private String value;

        StatusEnum(Integer code, String value) {
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