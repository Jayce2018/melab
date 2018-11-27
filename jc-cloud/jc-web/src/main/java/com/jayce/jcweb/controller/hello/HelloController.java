package com.jayce.jcweb.controller.hello;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/hello")
@RestController
public class HelloController {

    @RequestMapping(value = "/say/hello", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "sayHello", notes = "sayHello", produces = "application/json")
    public String sayHello() {
        //System.out.println("Hello World!");
        return "Hello World!";
    }
}
