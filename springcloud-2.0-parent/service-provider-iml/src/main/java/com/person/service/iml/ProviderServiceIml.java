package com.person.service.iml;

import com.person.api.entity.UserEntity;
import com.person.api.service.IProviderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderServiceIml implements IProviderService {

    @RequestMapping("/")
    @Override
    public String index() {
        return "提供服务";
    }

    @RequestMapping("/getInstance")
    @Override
    public UserEntity getInstance(@RequestParam("name")String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setAge(18);
        return userEntity;
    }

    /**
     * 没有解决服务雪崩效应
     * @return
     */
    @RequestMapping("userInfo")
    @Override
    public UserEntity getUserIfo() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("hq");
        userEntity.setAge(18);
        System.out.println("执行该任务的当前线程:"+Thread.currentThread().getName());
        return userEntity;
    }
    /**
     * 没有解决服务雪崩效应
     * @return
     */
    @RequestMapping("/msgprovider")
    @Override
    public String msgprovider() {

        try {
            System.out.println("获取信息，当前线程："+Thread.currentThread().getName());
            //当前线程睡眠1.5秒
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "获取信息成功";
    }
}
