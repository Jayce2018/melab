package com.jayce.jcemqconsumer.mq.producer.controller;

import com.jayce.jcemqconsumer.mq.producer.service.ProducerService;
import com.jayce.jcemqconsumer.mq.producer.vo.dto.MessageVO;
import com.jayce.jcemqconsumer.mq.producer.vo.http.ProducerRequest;
import com.jayce.jcemqconsumer.mq.producer.vo.http.ProducerResponse;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping(value = "/producer")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    @ApiOperation(value = "开始", notes = "开始", produces = "application/json")
    public ProducerResponse start(@RequestBody ProducerRequest request) {
        //参数初始化
        ProducerResponse response = new ProducerResponse();
        MessageVO messageVO=new MessageVO();
        messageVO.setId(11000);
        messageVO.setContent("测试发给客户端消息内容："+request.getMessage());
        messageVO.setCreateTime(new Date());
        producerService.sendSyncMessage("1008600",messageVO);
        return response;
    }

    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    @ApiOperation(value = "结束", notes = "二维码结束消费按钮", produces = "application/json")
    public void stop(@RequestBody ProducerRequest request) {
    }
}
