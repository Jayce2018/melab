package com.example.jctest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JcTextApplication {
    public static void main(String[] args) {
        SpringApplication.run(JcTextApplication.class, args);
    }
}
