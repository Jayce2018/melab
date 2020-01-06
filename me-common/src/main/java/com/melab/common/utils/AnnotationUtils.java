package com.melab.common.utils;

import com.melab.common.annotation.Author;
import com.melab.common.entity.vo.extra.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class AnnotationUtils {


    private static void authorCases() {
        Class<AnnotationUtils> cl = AnnotationUtils.class;
        for (Method m : cl.getDeclaredMethods()) {
            //获得注解的对象
            Author author = m.getAnnotation(Author.class);
            if (author != null) {
                System.out.println("Found Author Case:" + author.value());
            }
        }
    }


    @Author(value = "jayce")
    public void authorMethod(String author) {
    }

    @Test
    void test() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        person.setPersonId(1L);
        authorCases();
    }
}