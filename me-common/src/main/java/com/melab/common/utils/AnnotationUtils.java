package com.melab.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.melab.common.annotation.Author;
import com.melab.common.entity.vo.extra.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
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

    private static void enumCases(Person person) throws NoSuchFieldException, IllegalAccessException {
        Class<? extends Person> clazz = person.getClass();

        Field gender = clazz.getDeclaredField("gender");
        gender.setAccessible(true);
        Integer genderValue = (Integer) gender.get(person);
        String str = person.getByCode(genderValue).getValue();
        Field genderString = clazz.getDeclaredField("genderString");
        genderString.setAccessible(true);
        genderString.set(person, str);

        System.out.println(JSONObject.toJSON(person));
    }

    @Author(value = "jayce")
    public void authorMethod(String author) {
    }

    @Test
    void test() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        person.setPersonId(1L);
        person.setGender(1);
        enumCases(person);
        //authorCases();
    }
}