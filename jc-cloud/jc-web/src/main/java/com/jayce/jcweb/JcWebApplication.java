package com.jayce.jcweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients({"com.jayce.feign.feign"})
//@MapperScan("com.yc.cloud.bag.ges.common.*")
//@ComponentScan({"com.jayce.feign"})
public class JcWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JcWebApplication.class, args);
    }
}
