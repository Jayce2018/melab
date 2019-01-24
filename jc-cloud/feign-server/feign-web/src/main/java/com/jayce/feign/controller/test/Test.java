package com.jayce.feign.controller.test;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/test")
@RestController
public class Test {

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ApiOperation(value = "hello", notes = "测试", produces = "application/json")
    public String hello(){
        return "Hello world!";
    }

    @RequestMapping(value = "/feign", method = RequestMethod.POST)
    @ApiOperation(value = "feign", notes = "测试", produces = "application/json")
    public String feign(){
        return "feign调用成功";
    }

    @RequestMapping(value = "/exception", method = RequestMethod.POST)
    @ApiOperation(value = "exception", notes = "exception测试", produces = "application/json")
    public String exception() throws Exception {
        throw new Exception("异常测试！");
    }


}
