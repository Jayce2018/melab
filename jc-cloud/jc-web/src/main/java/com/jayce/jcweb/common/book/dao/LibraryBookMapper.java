package com.jayce.jcweb.common.book.dao;

import com.jayce.jcweb.common.book.entity.LibraryBook;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface LibraryBookMapper extends Mapper<LibraryBook> {
}