package com.exercise.model.test;

import com.alibaba.fastjson.JSONObject;
import com.exercise.model.generic.vo.Book;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiModel( discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {
    public static void main(String[] args) {
        List<Book> bookList=new ArrayList<>();
        int num=5;
        for(int i=1;i<=num;i++){
            Book book=new Book();
            book.setBookId((long) i);
            book.setAuthor("作者"+i);
            book.setName("书名"+i);
            bookList.add(book);
        }

        Map<Long, Book> bookMap = bookList.stream().collect(Collectors.toMap(Book::getBookId, b -> b, (k1, k2) -> k1));
        System.out.println(bookMap+"\n"+JSONObject.toJSON(bookMap));
    }
}
