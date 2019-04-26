package com.jayce.feign.common.book.entity;

import com.jayce.feign.common.book.vo.extra.LibraryBookExtra;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "library_book")
@Data
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
}