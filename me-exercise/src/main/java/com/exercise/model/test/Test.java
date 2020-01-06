package com.exercise.model.test;

import io.swagger.annotations.ApiModel;

import java.io.IOException;
import java.util.LinkedList;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IOException {
        Book book=new Book();
        book.setId(1L);
        System.out.println(book.hashCode());
        Book book1=new Book();
        book1.setId(1L);
        System.out.println(book1.hashCode());
        LinkedList<Integer> linkedList=new LinkedList<>();
        linkedList.add(1);
        System.out.println(linkedList.hashCode());
        LinkedList<Integer> linkedList1=new LinkedList<>();
        linkedList1.add(1);
        System.out.println(linkedList1.hashCode());
    }
}

