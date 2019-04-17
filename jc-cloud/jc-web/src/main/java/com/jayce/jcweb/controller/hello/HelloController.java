package com.jayce.jcweb.controller.hello;

import com.jayce.feign.common.usual.vo.UsualFeignVO;
import com.jayce.feign.feign.service.test.TestService;
import com.jayce.jcweb.common.usual.vo.request.UsualRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/hello")
@RestController
public class HelloController {
    @Autowired
    private TestService testFeignService;

    @RequestMapping(value = "/say/hello", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "sayHello", notes = "sayHello", produces = "application/json")
    public String sayHello() {
        return testFeignService.hello();
    }

    @RequestMapping(value = "/message", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "message", notes = "message", produces = "application/json")
    public String message(@Validated(UsualRequest.Message.class)@RequestBody UsualRequest request) {
        UsualFeignVO usualFeignVO=new UsualFeignVO();
        usualFeignVO.setMessage("");
        usualFeignVO.setCode("code");
        String result = testFeignService.message(usualFeignVO);
        result+="后缀";
        return result;
    }

}
