package com.qlp.cloud.provider.quartz.feign;

import com.qlp.cloud.common.core.constant.SecurityConstants;
import com.qlp.cloud.common.core.constant.ServiceNameConstants;
import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.provider.quartz.feign.factory.RemoteBookAttrServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 书籍属性接口调用
 */
@FeignClient(contextId = "remoteBookAttrService", value = ServiceNameConstants.OA_SERVICE, fallbackFactory = RemoteBookAttrServiceFallbackFactory.class)
public interface RemoteBookAttrService {
    @GetMapping("/attr/list")
    R list(@RequestHeader(SecurityConstants.FROM) String from);

    @GetMapping("/attr/list/{name}")
    R findAttrByName(@PathVariable("name") String name, @RequestHeader(SecurityConstants.FROM) String from);
}
