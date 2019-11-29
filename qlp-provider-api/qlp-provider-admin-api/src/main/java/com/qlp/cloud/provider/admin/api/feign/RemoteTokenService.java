package com.qlp.cloud.provider.admin.api.feign;

import com.qlp.cloud.provider.admin.api.feign.factory.RemoteTokenServiceFallbackFactory;
import com.qlp.cloud.common.core.constant.SecurityConstants;
import com.qlp.cloud.common.core.constant.ServiceNameConstants;
import com.qlp.cloud.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(contextId = "remoteTokenService", value = ServiceNameConstants.AUTH_SERVICE, fallbackFactory = RemoteTokenServiceFallbackFactory.class)
public interface RemoteTokenService {
    /**
     * 分页查询token 信息
     *
     * @param params 分页参数
     * @param from   内部调用标志
     * @return page
     */
    @PostMapping("/token/page")
    R getTokenPage(@RequestBody Map<String, Object> params, @RequestHeader(SecurityConstants.FROM) String from);

    /**
     * 删除token
     *
     * @param token token
     * @param from  调用标志
     * @return
     */
    @DeleteMapping("/token/{token}")
    R<Boolean> removeToken(@PathVariable("token") String token, @RequestHeader(SecurityConstants.FROM) String from);
}
