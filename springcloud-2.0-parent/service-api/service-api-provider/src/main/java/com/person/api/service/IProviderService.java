package com.person.api.service;

import com.person.api.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 建议：实体类放在api层，易于依赖
 */
public interface IProviderService {

    @RequestMapping("/")
    public String index();
    @RequestMapping("/getInstance")
    public UserEntity getInstance(@RequestParam("name") String name);
    @RequestMapping("/userInfo")
    public UserEntity getUserIfo();

    @RequestMapping("/msgprovider")
    public String msgprovider();
}
