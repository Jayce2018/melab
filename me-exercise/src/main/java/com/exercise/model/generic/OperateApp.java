package com.exercise.model.generic;

import com.alibaba.fastjson.JSONObject;
import com.exercise.model.generic.biz.Operate;
import com.exercise.model.generic.vo.Book;

import java.util.ArrayList;
import java.util.List;

public class OperateApp {
    private static Operate operate = new Operate();

    public static void main(String[] args) throws Exception {
        Book book = new Book();
        book.setBookId(10086L);
        book.setName("圣经");
        book.setAuthor("Jess");
        String string = operate.toJsonString(book);
        System.out.println(string);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book);
        Book book1 = operate.getLast(bookList);
        System.out.println(JSONObject.toJSON(book1).toString());
    }
}
