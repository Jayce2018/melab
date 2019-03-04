package com.exercise.model.annotation.test;

import com.alibaba.fastjson.JSONObject;
import com.exercise.model.annotation.AddEnum;
import com.exercise.model.generic.vo.Book;
import io.swagger.annotations.ApiModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@ApiModel( discriminator = "sun jie 2019/3/4 0004 10:31", description = "实体注解反射追加字段注解")
public class AnnoTest {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        //假定被拦截的实体
        Book book = new Book();
        book.setBookId(10086L);
        book.setName("ice cream");
        book.setType(1);
        book.setAuthor("艾斯");
        //解析注解
        Class<? extends Book> bookClass = book.getClass();
        Field[] fields = bookClass.getDeclaredFields();
        //指定字段存在标记
        int flag=0;
        //判断哪个字段存在指定注解
        for (Field field : fields) {
            AddEnum fieldAnnotation = field.getAnnotation(AddEnum.class);
            //存在指定注解
            if (null != fieldAnnotation) {
                //注解参数
                Class<? extends Enum> enumClass = fieldAnnotation.enumClass();
                String keys = fieldAnnotation.keys();
                field.setAccessible(true);
                Object reCode = field.get(book);
                System.out.println("原枚举code:"+reCode);
                //解析自定义构造器+获取方法
                Enum[] enumConstants = enumClass.getEnumConstants();
                Method getValueMethod = enumClass.getMethod("getValue");
                Method getCodeMethod = enumClass.getMethod("getCode");
                System.out.println("方法：" + getValueMethod.getName());


                //逻辑
                String dictionary = "";
                if ("".equals(keys)) {
                    dictionary = field.getName() + "Dictionary";
                } else {
                    dictionary = keys;
                }
                //指定字段赋值
                field.setAccessible(true);
                for (Field fieldSon : fields) {
                    if (fieldSon.getName().equals(dictionary)) {
                        fieldSon.setAccessible(true);
                        Field[] declaredFields = enumClass.getDeclaredFields();
                        enumClass.getEnumConstants();
                        for(Enum enumConstant:enumConstants){
                            Object code = getCodeMethod.invoke(enumConstant);
                            Object value = getValueMethod.invoke(enumConstant);
                            System.out.println("code:"+code+"-->value:"+value);
                            //追加字典
                            if(code.equals(reCode)) {
                                fieldSon.set(book, value);
                            }
                        }
                        flag=1;
                    }
                }
            }
        }

        if(flag==0){
            log.println("字典聚类==>"+new Date() +" [指定keys字段不存在或字典未添加!] ");
        }
        //打印结果
        System.out.println("最终结果=========>" + JSONObject.toJSON(book));
    }
}
