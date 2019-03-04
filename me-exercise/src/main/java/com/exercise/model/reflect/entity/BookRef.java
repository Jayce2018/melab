package com.exercise.model.reflect.entity;

import com.exercise.model.generic.vo.Book;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static com.exercise.model.generic.vo.Book.TypeEnum.TYPE_ENUM_ONE;

public class BookRef {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //常规类反射测试
        Book bookIns=new Book();
        bookIns.setBookId(10086L);
        bookIns.setName("科学趣谈");
        Class<? extends Book> bookInsClass = bookIns.getClass();
        Method getNameMethod = bookInsClass.getMethod("getName");
        Object bookName = getNameMethod.invoke(bookIns);
        //System.out.println(bookName);

        //枚举实例测试
        Class<? extends Book.TypeEnum> type_enum_oneClass = TYPE_ENUM_ONE.getClass();
        //--//构造器+方法
        Book.TypeEnum[] typeEnums = type_enum_oneClass.getEnumConstants();
        Method getValueMethod = type_enum_oneClass.getMethod("getValue");

        for(Book.TypeEnum typeEnum:typeEnums){
            System.out.println(getValueMethod.invoke(typeEnum));
        }

    }
}
