package com.exercise.model.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Book {
    private Integer bookId;
    private String name;
    private String author;
    private Double price;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

public class Test {

    public static void main(String[] args) {
        String[] strings = null;
        System.out.println(Arrays.toString(strings));

        List<Book> books = new ArrayList<Book>();

        Book book1 = new Book();
        book1.setBookId(1);
        book1.setName("简·爱");
        book1.setAuthor("Auth");
        book1.setPrice(6.66);
        books.add(book1);

        Book book = new Book();
        book.setBookId(2);
        book.setName("三国演义");
        book.setAuthor("罗贯中");
        book.setPrice(8.88);
        books.add(book);

        //System.out.println(JSONObject.toJSON(books));
        long book2 = books.stream().filter(fl -> fl.getPrice() == 6.66).count();
        //System.out.println(JSONObject.toJSON(book2));

        Map map = books.stream().collect(Collectors.toMap(Book::getBookId, Book::getAuthor));
        //System.out.println(map.get(1));
    }

    public static void printValur(String str) {
        System.out.println("print value : " + str);
    }

    /*public static void main(String[] args) {
        List<String> al = Arrays.asList("a", "b", "c", "d");
        al.forEach(AcceptMethod::printValur);
        //下面的方法和上面等价的
        Consumer<String> methodParam = AcceptMethod::printValur; //方法参数
        al.forEach(x -> methodParam.accept(x));//方法执行accept
    }*/


}
