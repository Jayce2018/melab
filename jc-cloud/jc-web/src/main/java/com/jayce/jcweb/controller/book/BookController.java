package com.jayce.jcweb.controller.book;

import com.jayce.jcweb.common.book.entity.LibraryBook;
import com.jayce.jcweb.common.book.service.LibraryBookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/book")
@RestController
public class BookController {
    @Autowired
    private LibraryBookService libraryBookService;

    @RequestMapping(value = "/bookList", method = RequestMethod.POST)
    @ApiOperation(value = "bookList", notes = "获取所有图书", produces = "application/json")
    public List<LibraryBook> bookList(){
        return libraryBookService.selectListAll();
    }

}
