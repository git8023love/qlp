package com.qlp.cloud.auth;

import com.qlp.cloud.common.security.annotation.EnableQlpFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 认证授权中心
 */
@SpringCloudApplication
@EnableQlpFeignClients
public class QlpAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(QlpAuthApplication.class, args);
    }
}
