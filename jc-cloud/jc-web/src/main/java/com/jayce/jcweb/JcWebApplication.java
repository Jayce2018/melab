package com.jayce.jcweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JcWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(JcWebApplication.class, args);
    }
}
