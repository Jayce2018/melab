package com.jayce.ribbonserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "ribbon-consumer", method = RequestMethod.GET)
    public String hello() {
        return restTemplate.getForEntity("http://JC-WEB/hello/say/hello", String.class).getBody();
    }
}
