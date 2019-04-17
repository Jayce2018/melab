package com.jayce.jcgate;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
@EnableEurekaClient
public class JcGateApplication {

    public static void main(String[] args) {
        SpringApplication.run(JcGateApplication.class, args);
    }

}
