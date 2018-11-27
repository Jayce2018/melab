package com.melab.common.utils;

import com.melab.common.annotation.EnumAn;
import com.melab.common.entity.vo.extra.Person;
import io.swagger.annotations.ApiModelProperty;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AnnotationDeals {
    @ApiModelProperty(value = "", notes = "统一注解处理", reference = "*")
    private <T> T dictionary(T t) throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            Class<EnumAn> clazz = EnumAn.class;
            EnumAn obj = field.getAnnotation(clazz);
            if (null != obj) {
                field.setAccessible(true);
                Integer fieldNum = (Integer) field.get(t);
                //System.out.println(fieldNum);
                Class<? extends Enum> enumClass = obj.keyEnumClass();
                Field[] enumFields = enumClass.getDeclaredFields();
                for (Field enumField : enumFields) {
                    if (enumField.getType().isEnum()) {
                        Enum anEnum = (Enum) enumField.get(enumClass);
                        Enum[] enums = enumClass.getEnumConstants();

                        System.out.println(Arrays.toString(enums));
                    }
                }
                //System.out.println(Arrays.toString(enumFields));
                Field fieldString = t.getClass().getDeclaredField(field.getName() + "String");
                fieldString.setAccessible(true);
                fieldString.set(t, "fieldString");
            }
        }
        return t;
    }

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        Person person = new Person();
        person.setGender(1);
        Person personNew = dictionary(person);
        //System.out.println(JSONObject.toJSON(personNew));
    }

}
