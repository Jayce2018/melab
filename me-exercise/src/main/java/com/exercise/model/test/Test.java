package com.exercise.model.test;

import com.exercise.model.generic.vo.Book;
import io.swagger.annotations.ApiModel;

import java.util.HashMap;
import java.util.Map;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {

    public static void main(String[] args) {
        Map<Integer,Book> map=new HashMap<>();
        Book book=new Book();
        book.setBookId((long) 1);
        book.setName("name");
        map.put(1,book);

        Book book2=map.get(1);
        book2.setAuthor("author");

        System.out.println(map);
    }


}
