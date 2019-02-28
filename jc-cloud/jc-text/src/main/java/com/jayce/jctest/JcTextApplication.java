package com.jayce.jctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.jayce.common.aspect","com.jayce.jctest"})
public class JcTextApplication {
    public static void main(String[] args) {
        SpringApplication.run(JcTextApplication.class, args);
    }
}
