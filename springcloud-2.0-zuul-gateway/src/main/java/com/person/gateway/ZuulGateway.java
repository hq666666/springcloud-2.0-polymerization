package com.person.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy //开启zuul服务代理
public class ZuulGateway {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGateway.class,args);
    }
}
