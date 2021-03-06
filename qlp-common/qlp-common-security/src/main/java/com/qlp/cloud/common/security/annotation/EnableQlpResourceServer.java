package com.qlp.cloud.common.security.annotation;

import com.qlp.cloud.common.security.component.QlpResourceServerAutoConfiguration;
import com.qlp.cloud.common.security.component.QlpSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * 资源服务注解
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({QlpResourceServerAutoConfiguration.class, QlpSecurityBeanDefinitionRegistrar.class})
public @interface EnableQlpResourceServer {
}
