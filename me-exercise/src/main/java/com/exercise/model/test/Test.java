package com.exercise.model.test;

import com.alibaba.fastjson.JSONObject;
import com.exercise.model.generic.vo.Book;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {
    public static void main(String[] args) {
        Book book = new Book();
        book.setName("book");
        book.setAuthor("author");
        book.setBookId(1L);
        Book book1=JSONObject.parseObject(JSONObject.toJSON(book).toString(),Book.class);
        book1.setBookId(2L);
        System.out.println(JSONObject.toJSON(book));
        System.out.println(JSONObject.toJSON(book1));
    }
}
