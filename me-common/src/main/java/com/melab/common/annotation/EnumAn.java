package com.melab.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE_PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumAn {

    Class<? extends Enum> keyEnumClass() default Enum.class;

    String keyString() default "";

    Class<? extends Object> dictionaryClass() default Object.class;

    String dictionaryMethod() default "";

    String targetField() default "";

    boolean isValueNeedMerge() default false;
}
