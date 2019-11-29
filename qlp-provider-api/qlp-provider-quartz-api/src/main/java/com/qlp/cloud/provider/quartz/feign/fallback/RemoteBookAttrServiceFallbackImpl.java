package com.qlp.cloud.provider.quartz.feign.fallback;

import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.provider.quartz.feign.RemoteBookAttrService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteBookAttrServiceFallbackImpl implements RemoteBookAttrService {
    @Setter
    private Throwable cause;

    @Override
    public R list(String from) {
        log.error("feign 查询书籍属性失败", cause);
        return null;
    }

    @Override
    public R findAttrByName(String name, String from) {
        log.error("feign 查询书籍属性失败: name = {}", name, cause);
        return null;
    }
}
