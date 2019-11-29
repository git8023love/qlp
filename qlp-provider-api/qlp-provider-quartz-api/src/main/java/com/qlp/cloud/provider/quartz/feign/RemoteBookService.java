package com.qlp.cloud.provider.quartz.feign;

import com.qlp.cloud.common.core.constant.SecurityConstants;
import com.qlp.cloud.common.core.constant.ServiceNameConstants;
import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.provider.quartz.feign.factory.RemoteBookServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * 书籍信息接口调用
 */
@FeignClient(contextId = "remoteBookService", value = ServiceNameConstants.OA_SERVICE, fallbackFactory = RemoteBookServiceFallbackFactory.class)
public interface RemoteBookService {

    @PostMapping("/book/save")
    R save(@RequestBody Map<String, Object> map, @RequestHeader(SecurityConstants.FROM) String from);

}
