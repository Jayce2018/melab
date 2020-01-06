package com.jayce.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE_PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FieldDict {

    //翻译字段名,默认（fieldString）
    String dictFieldName() default "";

    //字典类
    Class<?> dictionaryClass() default Object.class;

    //获取code的方法名
    String codeMethodName() default "getCode";

    //获取value的方法名
    String valueMethodName() default "getValue";

    boolean isValueNeedMerge() default false;
}
