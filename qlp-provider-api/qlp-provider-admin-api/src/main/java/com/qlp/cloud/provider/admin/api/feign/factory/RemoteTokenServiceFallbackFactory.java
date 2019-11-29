package com.qlp.cloud.provider.admin.api.feign.factory;

import com.qlp.cloud.provider.admin.api.feign.RemoteTokenService;
import com.qlp.cloud.provider.admin.api.feign.fallback.RemoteTokenServiceFallbackImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class RemoteTokenServiceFallbackFactory implements FallbackFactory<RemoteTokenService> {

    @Override
    public RemoteTokenService create(Throwable throwable) {
        RemoteTokenServiceFallbackImpl remoteTokenServiceFallback = new RemoteTokenServiceFallbackImpl();
        remoteTokenServiceFallback.setCause(throwable);
        return remoteTokenServiceFallback;
    }
}
