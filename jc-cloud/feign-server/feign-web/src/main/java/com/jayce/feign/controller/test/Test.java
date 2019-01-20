package com.jayce.feign.controller.test;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/test")
public class Test {

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ApiOperation(value = "hello", notes = "测试", produces = "application/json")
    public String hello(){
        return "Hello world!";
    }


}
