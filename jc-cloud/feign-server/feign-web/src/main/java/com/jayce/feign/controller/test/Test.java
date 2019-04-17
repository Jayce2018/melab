package com.jayce.feign.controller.test;

import com.jayce.common.util.base.exception.BaseException;
import com.jayce.common.util.base.exception.ExceptionCodeUtil;
import com.jayce.feign.common.usual.vo.UsualFeignVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
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
        //throw new Exception(URLEncoder.encode("异常测试！", "UTF-8"));
        throw new BaseException(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_CHECK_ERROR, "校验信息为空");
    }

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    @ApiOperation(value = "message", notes = "message测试", produces = "application/json")
    public String message(@Validated(UsualFeignVO.Message.class)@RequestBody UsualFeignVO usualFeignVO) throws Exception {
        String message = usualFeignVO.getMessage();
        String code = usualFeignVO.getCode();
        message=(null!=code)?(code+message):message;
        return message;
    }


}
