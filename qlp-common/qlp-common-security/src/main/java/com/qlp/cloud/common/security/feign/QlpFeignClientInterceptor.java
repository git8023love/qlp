package com.qlp.cloud.common.security.feign;

import cn.hutool.core.collection.CollUtil;
import com.qlp.cloud.common.core.constant.SecurityConstants;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.util.Collection;

/**
 * 扩展OAuth2FeignRequestInterceptor
 */
@Slf4j
public class QlpFeignClientInterceptor extends OAuth2FeignRequestInterceptor {
    private final OAuth2ClientContext oAuth2ClientContext;
    private final AccessTokenContextRelay accessTokenContextRelay;

    /**
     * 在授权头中使用提供的OAuth2ClientContext和承载令牌的默认构造函数
     *
     * @param oAuth2ClientContext     提供的上下文
     * @param resource                访问的资源类型
     * @param accessTokenContextRelay
     */
    public QlpFeignClientInterceptor(OAuth2ClientContext oAuth2ClientContext
            , OAuth2ProtectedResourceDetails resource, AccessTokenContextRelay accessTokenContextRelay) {
        super(oAuth2ClientContext, resource);
        this.oAuth2ClientContext = oAuth2ClientContext;
        this.accessTokenContextRelay = accessTokenContextRelay;
    }

    /**
     * 使用提供的名称和提取头创建模板
     * 1. 如果使用 非web 请求，header 区别
     * 2. 根据authentication 还原请求token
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        Collection<String> fromHeader = template.headers().get(SecurityConstants.FROM);
        if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.FROM_IN)) {
            return;
        }
        accessTokenContextRelay.copyToken();
        if (oAuth2ClientContext != null
                && oAuth2ClientContext.getAccessToken() != null) {
            super.apply(template);
        }
    }
}
