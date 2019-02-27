package com.jayce.jcweb.common.book.service;

import com.jayce.common.util.base.service.BaseService;
import com.jayce.jcweb.common.book.dao.LibraryBookMapper;
import com.jayce.jcweb.common.book.entity.LibraryBook;
import org.springframework.stereotype.Service;

@Service
public class LibraryBookService extends BaseService<LibraryBookMapper, LibraryBook> {
}
