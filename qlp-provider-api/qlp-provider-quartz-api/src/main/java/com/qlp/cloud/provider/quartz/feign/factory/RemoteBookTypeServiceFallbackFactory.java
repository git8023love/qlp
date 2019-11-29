package com.qlp.cloud.provider.quartz.feign.factory;

import com.qlp.cloud.provider.quartz.feign.RemoteBookTypeService;
import com.qlp.cloud.provider.quartz.feign.fallback.RemoteBookTypeServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteBookTypeServiceFallbackFactory implements FallbackFactory<RemoteBookTypeService> {
    @Override
    public RemoteBookTypeService create(Throwable throwable) {
        RemoteBookTypeServiceFallbackImpl remoteBookTypeServiceFallback = new RemoteBookTypeServiceFallbackImpl();
        remoteBookTypeServiceFallback.setCause(throwable);
        return remoteBookTypeServiceFallback;
    }
}
