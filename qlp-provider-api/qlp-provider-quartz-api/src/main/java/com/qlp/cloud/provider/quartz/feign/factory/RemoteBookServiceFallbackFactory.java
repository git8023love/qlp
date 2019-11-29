package com.qlp.cloud.provider.quartz.feign.factory;

import com.qlp.cloud.provider.quartz.feign.RemoteBookService;
import com.qlp.cloud.provider.quartz.feign.fallback.RemoteBookServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteBookServiceFallbackFactory implements FallbackFactory<RemoteBookService> {
    @Override
    public RemoteBookService create(Throwable throwable) {
        RemoteBookServiceFallbackImpl remoteBookServiceFallback = new RemoteBookServiceFallbackImpl();
        remoteBookServiceFallback.setCause(throwable);
        return remoteBookServiceFallback;
    }
}
