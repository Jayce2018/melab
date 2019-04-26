package com.jayce.jcweb.controller.test;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.jayce.common.util.base.exception.BaseException;
import com.jayce.common.util.base.exception.ExceptionCodeUtil;
import com.jayce.feign.common.usual.vo.UsualFeignVO;
import com.jayce.feign.feign.service.test.TestService;
import com.jayce.jcweb.common.usual.vo.request.UsualRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feign", method = {RequestMethod.POST, RequestMethod.GET})
public class WebFeignController {

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

    @RequestMapping(value = "/insertBook", method = RequestMethod.POST)
    @ApiOperation(value = "insertBook", notes = "feign新增书目", produces = "application/json")
    @LcnTransaction
    @Transactional(rollbackFor = Exception.class)
    public void insertBook(@Validated(UsualRequest.InsertBook.class)@RequestBody UsualRequest usualRequest){
        UsualFeignVO usualFeignVO=new UsualFeignVO();
        usualFeignVO.setBookName(usualRequest.getBookName());
        testService.feignBookInsert(usualFeignVO);
        if(usualRequest.getType()==-1) {
            throw new BaseException(ExceptionCodeUtil.ExceptionTypeEnum.EXCEPTION_TYPE_CHECK_ERROR, "校验信息为空");
        }
    }
}
