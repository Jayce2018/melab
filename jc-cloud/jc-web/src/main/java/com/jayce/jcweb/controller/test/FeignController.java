package com.jayce.jcweb.controller.test;

import com.jayce.feign.feign.service.test.TestService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feign", method = {RequestMethod.POST, RequestMethod.GET})
public class FeignController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "sayHello", notes = "sayHello", produces = "application/json")
    public String test() {
        return testService.feign();
    }

    @RequestMapping(value = "/exception", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "exception", notes = "异常测试", produces = "application/json")
    public String exception() {
        return testService.exception();
    }
}
