package com.qlp.cloud.provider.quartz.feign.fallback;

import cn.hutool.json.JSONUtil;
import com.qlp.cloud.common.core.util.R;
import com.qlp.cloud.provider.quartz.dto.BookTypeDTO;
import com.qlp.cloud.provider.quartz.feign.RemoteBookService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class RemoteBookServiceFallbackImpl implements RemoteBookService {
    @Setter
    private Throwable cause;

    @Override
    public R save(Map<String, Object> map, String from) {
        log.error("feign 保存书籍失败：book = {}", JSONUtil.toJsonStr(map), cause);
        return null;
    }
}
