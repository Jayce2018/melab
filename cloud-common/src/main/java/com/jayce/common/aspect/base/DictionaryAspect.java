package com.jayce.common.aspect.base;

import com.jayce.common.util.base.annotation.AnnotationDeal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ApiModel(value = "DictionaryAspect", description = "字典切面")
@Slf4j
@Order(2)
public class DictionaryAspect {


    @ApiOperation(value = "webLog", notes = "定义方法翻译注解切点")
    @Pointcut("@annotation(com.jayce.common.annotation.Translation)")
    public void dictionaryCut() {
    }

    @Around("dictionaryCut()")
    @ApiOperation(value = "环信息通知", notes = "可选")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object object = pjp.proceed();
        AnnotationDeal annotationDeal=new AnnotationDeal();
        Object result = annotationDeal.dictionary(object);
        log.info("【翻译】"+result);
        return result;
    }


}
