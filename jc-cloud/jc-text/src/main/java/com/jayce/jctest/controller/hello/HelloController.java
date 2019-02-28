package com.jayce.jctest.controller.hello;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/hello", method = {RequestMethod.POST, RequestMethod.GET})
@RestController
public class HelloController {

    @RequestMapping(value = "/say/hello")
    @ApiOperation(value = "sayHello", notes = "sayHello", produces = "application/json")
    public String sayHello(@RequestParam String message) {
        return message;
    }
}
