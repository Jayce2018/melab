package com.exercise.model.jsontest;

import com.alibaba.fastjson.JSONArray;
import com.exercise.model.generic.vo.Book;

import java.util.ArrayList;
import java.util.List;

public class jsonTest {
    public static void main(String[] args) {
        Book book = new Book();
        List<Book> bookList = new ArrayList<>();
        String bookString = "[{'bookId':1008600,'name':'bookName1','author':'author2','description':'hahaha'},{'bookId':1008601,'name':'bookName2','author':'author2'}]";
        JSONArray jsonArray = JSONArray.parseArray(bookString);
        System.out.println("jsonArray==>" + jsonArray);
        List<Book> books = JSONArray.parseArray(bookString, Book.class);
        System.out.println(books.get(0).getBookId() + "<>" + books.get(0).getAuthor() + "<>" + books.get(0).getName());
    }
}
