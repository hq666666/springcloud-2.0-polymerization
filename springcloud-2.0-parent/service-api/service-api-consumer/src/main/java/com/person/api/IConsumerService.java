package com.person.api;


import org.springframework.web.bind.annotation.RequestMapping;

public interface IConsumerService {
   //让feign客户端调用
    @RequestMapping("/")
    public String index();
    @RequestMapping("/consume")
    public String consumer(String name);

    @RequestMapping("/info")
    public String getUserInfo();

   @RequestMapping("msginfo")
   public String getMessage();
}
