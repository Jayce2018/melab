package com.jayce.jcgate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableZuulProxy
@SpringCloudApplication
@EnableEurekaClient
@EnableFeignClients({"com.jayce.feign.feign"})
public class JcGateApplication {

    public static void main(String[] args) {
        SpringApplication.run(JcGateApplication.class, args);
    }

}

