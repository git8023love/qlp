package com.qlp.cloud.provider.quartz.feign.factory;

import com.qlp.cloud.provider.quartz.feign.RemoteBookAttrService;
import com.qlp.cloud.provider.quartz.feign.fallback.RemoteBookAttrServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteBookAttrServiceFallbackFactory implements FallbackFactory<RemoteBookAttrService> {
    @Override
    public RemoteBookAttrService create(Throwable throwable) {
        RemoteBookAttrServiceFallbackImpl bookAttrServiceFallback = new RemoteBookAttrServiceFallbackImpl();
        bookAttrServiceFallback.setCause(throwable);
        return bookAttrServiceFallback;
    }
}
