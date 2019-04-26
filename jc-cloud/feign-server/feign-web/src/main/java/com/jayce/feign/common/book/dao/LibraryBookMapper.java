package com.jayce.feign.common.book.dao;

import com.jayce.feign.common.book.entity.LibraryBook;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface LibraryBookMapper extends Mapper<LibraryBook> {
}