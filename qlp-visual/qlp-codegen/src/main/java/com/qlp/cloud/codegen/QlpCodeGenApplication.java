package com.qlp.cloud.codegen;

import com.qlp.cloud.common.security.annotation.EnableQlpFeignClients;
import com.qlp.cloud.common.security.annotation.EnableQlpResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 代码生成模块
 */
@EnableQlpFeignClients
@EnableQlpResourceServer
@SpringCloudApplication
public class QlpCodeGenApplication {
    public static void main(String[] args) {
        SpringApplication.run(QlpCodeGenApplication.class, args);
    }
}
