package com.jayce.jcweb;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients({"com.jayce.feign.feign"})
@MapperScan("com.jayce.jcweb.common.*")
@ComponentScan({"com.jayce.common.aspect","com.jayce.common.util.base","com.jayce.jcweb"})
//@EnableDistributedTransaction
public class JcWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(JcWebApplication.class, args);
    }
}
