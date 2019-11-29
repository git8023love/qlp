package com.qlp.cloud.provider.admin;


import com.qlp.cloud.common.security.annotation.EnableQlpFeignClients;
import com.qlp.cloud.common.security.annotation.EnableQlpResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 用户统一管理系统
 */
@EnableQlpResourceServer
@EnableQlpFeignClients
@SpringCloudApplication
public class QlpAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(QlpAdminApplication.class, args);
    }

}
