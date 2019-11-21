package com.person.service.feign;

import com.person.api.service.IProviderService;
import com.person.service.fallback.ProviderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider",fallback = ProviderServiceFallback.class)
public interface ProviderServiceFeign extends IProviderService {
}
