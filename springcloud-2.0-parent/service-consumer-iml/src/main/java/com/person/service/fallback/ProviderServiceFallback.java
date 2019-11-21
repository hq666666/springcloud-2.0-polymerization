package com.person.service.fallback;

import com.person.api.entity.UserEntity;
import com.person.api.service.IProviderService;
import com.person.service.feign.ProviderServiceFeign;
import org.springframework.stereotype.Component;

/**
 * fallback:
 *    服务降级类；
 *    注意：实现的接口服务是该对应feign客户端
 */
@Component
public class ProviderServiceFallback implements ProviderServiceFeign {

    @Override
    public String index() {
        return null;
    }

    @Override
    public UserEntity getInstance(String name) {
        return null;
    }

    @Override
    public UserEntity getUserIfo() {
        return null;
    }

    @Override
    public String msgprovider() {
        return "当前服务忙，请稍后重试";
    }
}
