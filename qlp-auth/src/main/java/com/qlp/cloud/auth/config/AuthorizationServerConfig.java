package com.qlp.cloud.auth.config;

import com.qlp.cloud.common.core.constant.SecurityConstants;
import com.qlp.cloud.common.security.component.QlpWebResponseExceptionTranslator;
import com.qlp.cloud.common.security.service.QlpClientDetailsService;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证服务器配置
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final RedisConnectionFactory redisConnectionFactory;

    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        QlpClientDetailsService clientDetailsService = new QlpClientDetailsService(dataSource);
        clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
        clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .allowFormAuthenticationForClients()
                .checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancer())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)
                .exceptionTranslator(new QlpWebResponseExceptionTranslator());
    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(SecurityConstants.PROJECT_PREFIX + SecurityConstants.OAUTH_PREFIX);
        return tokenStore;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(1);
            additionalInfo.put("license", SecurityConstants.PROJECT_LICENSE);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}
