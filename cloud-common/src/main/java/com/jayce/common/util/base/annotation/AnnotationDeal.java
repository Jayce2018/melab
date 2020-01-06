package com.jayce.common.util.base.annotation;

import com.jayce.common.VO.Person;
import com.jayce.common.annotation.FieldDict;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

//TODO 数组处理待优化
public class AnnotationDeal {
    @ApiModelProperty(value = "统一注解处理")
    public  <T> T dictionary(T t) throws NoSuchFieldException, IllegalAccessException, InstantiationException, InvocationTargetException {
        boolean isList = t instanceof List;
        //对象数组型
        if (isList) {
            List list = List.class.cast(t);
            //所有成员
            if (list.size() <= 0) {
                return t;
            }
            //字典类实例记录缓存
            Map<String, Map<Object, Object>> typeMap = new HashMap<>();
            for (Object obj : list) {
                Field[] fields = obj.getClass().getDeclaredFields();
                translation(obj, fields, typeMap);
            }
        } else {
            //单对象型;所有成员
            Field[] fields = t.getClass().getDeclaredFields();
            //字典类实例记录缓存
            Map<String, Map<Object, Object>> typeMap = new HashMap<>();
            translation(t, fields, typeMap);
        }
        return t;
    }

    @ApiOperation(value = "翻译")
    private <T> void translation(T t, Field[] fields, Map<String, Map<Object, Object>> map) throws NoSuchFieldException, IllegalAccessException, InstantiationException, InvocationTargetException {
        for (Field field : fields) {
            if (field.getType().isInstance(List.class)) {
                dictionary(field);
                continue;
            }
            Class<FieldDict> clazz = FieldDict.class;
            FieldDict fieldDict = field.getAnnotation(clazz);
            //存在翻译注解
            if (null != fieldDict) {
                field.setAccessible(true);
                //注解自定义属性获取
                Class<?> enumClass = fieldDict.dictionaryClass();
                String dictFieldName = fieldDict.dictFieldName();
                String codeMethodName = fieldDict.codeMethodName();
                String valueMethodName = fieldDict.valueMethodName();

                Field[] enumFields = enumClass.getDeclaredFields();
                //有实例缓存取直接取
                Map<Object, Object> enumMap = new HashMap<>();
                if (null != map && null != map.get(field.getName())) {
                    enumMap = map.get(field.getName());
                } else {
                    for (Field enumField : enumFields) {
                        enumField.setAccessible(true);

                        if (enumField.getType().isEnum()) {
                            Object object = enumField.get(clazz);
                            Object anEnum = enumClass.cast(object);
                            //调用方法解析枚举值
                            Method[] methods = enumClass.getDeclaredMethods();
                            Method getCode = null;
                            Method getvalue = null;
                            try {
                                getCode = Arrays.stream(methods).filter(fl -> codeMethodName.equals(fl.getName())).collect(Collectors.toList()).get(0);
                                getvalue = Arrays.stream(methods).filter(fl -> valueMethodName.equals(fl.getName())).collect(Collectors.toList()).get(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            assert getCode != null;
                            assert getvalue != null;
                            enumMap.put(getCode.invoke(anEnum), getvalue.invoke(anEnum));
                        }
                    }
                    if (map != null) {
                        map.put(field.getName(), enumMap);
                    }
                }
                //设置翻译字段
                dictFieldName = StringUtils.isEmpty(dictFieldName) ? field.getName() + "String" : dictFieldName;
                Field fieldString = t.getClass().getDeclaredField(dictFieldName);
                fieldString.setAccessible(true);
                fieldString.set(t, enumMap.get(field.get(t)));
            }

        }
    }

    @Test
    public void start() throws Exception {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i <10000 ; i++) {
            Person person1 = new Person();
            person1.setGender(1);
            personList.add(person1);
        }

        personList = dictionary(personList);
        System.out.println(personList);

        /*Person person = new Person();
        person.setGender(3);
        person.setList(null);
        Person personNew = dictionary(person);
        System.out.println(personNew);*/
    }
}
