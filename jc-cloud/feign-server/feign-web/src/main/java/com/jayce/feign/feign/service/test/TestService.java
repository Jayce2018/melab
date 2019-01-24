package com.jayce.feign.feign.service.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ApiModel( discriminator = "@Author:sun jie 2019/1/20 0020 17:33",value = "TestService", description = "测试feign")
@FeignClient(value = "feign-web")
public interface TestService {

    @RequestMapping(value = "/test/hello", method = RequestMethod.POST)
    @ApiOperation(value = "hello", notes = "测试feign")
    String hello();

    @RequestMapping(value = "/test/feign", method = RequestMethod.POST)
    @ApiOperation(value = "feign", notes = "测试feign")
    String feign();

    @RequestMapping(value = "/test/exception", method = RequestMethod.POST)
    @ApiOperation(value = "exception", notes = "测试feignException")
    String exception();
}
