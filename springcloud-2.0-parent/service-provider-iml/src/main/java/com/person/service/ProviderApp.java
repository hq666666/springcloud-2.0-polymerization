package com.person.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * maven多模块搭建出现的问题：
 *        找不到main类；
 *     解决方案(cause：本项目依赖的一个项目没找到对应的jar包)：
 *         maven clean -Dskip.test install;
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApp.class,args);
    }
}
