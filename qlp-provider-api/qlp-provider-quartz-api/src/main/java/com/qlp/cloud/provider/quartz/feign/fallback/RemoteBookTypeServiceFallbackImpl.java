package com.qlp.cloud.provider.quartz.feign.fallback;

import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.provider.quartz.feign.RemoteBookTypeService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RemoteBookTypeServiceFallbackImpl implements RemoteBookTypeService {
    @Setter
    private Throwable cause;

    @Override
    public R listBookType(Integer type, String from) {
        log.error("feign 查询书籍分类失败:{}", type, cause);
        return null;
    }

    @Override
    public R save(Long id, String name, Integer type, String from) {
        log.error("feign 保存书籍分类失败:id = {}， name = {}, type = {}",id, name, type, cause);
        return null;
    }
}
