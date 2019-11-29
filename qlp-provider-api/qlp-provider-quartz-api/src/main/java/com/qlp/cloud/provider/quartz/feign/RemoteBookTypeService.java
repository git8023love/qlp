package com.qlp.cloud.provider.quartz.feign;

import com.qlp.cloud.common.core.constant.SecurityConstants;
import com.qlp.cloud.common.core.constant.ServiceNameConstants;
import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.provider.quartz.feign.factory.RemoteBookTypeServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 书籍分类接口调用
 */
@FeignClient(contextId = "remoteBookTypeService", value = ServiceNameConstants.OA_SERVICE, fallbackFactory = RemoteBookTypeServiceFallbackFactory.class)
public interface RemoteBookTypeService {

    @GetMapping("/bookType/list/{type}")
    R listBookType(@PathVariable("type") Integer type, @RequestHeader(SecurityConstants.FROM) String from);

    @PostMapping("/bookType/save")
    R save(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("type") Integer type, @RequestHeader(SecurityConstants.FROM) String from);
}
