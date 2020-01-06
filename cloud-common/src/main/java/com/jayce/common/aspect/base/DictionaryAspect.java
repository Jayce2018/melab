package com.jayce.common.aspect.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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

    @ApiOperation(value = "webLog", notes = "定义jc-cloud的控制层切点")
    @Pointcut("execution(* com.jayce..*Controller.*(..))")
    public void dictionary() {
    }


}
