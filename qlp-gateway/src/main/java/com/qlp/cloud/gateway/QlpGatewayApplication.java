package com.qlp.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 网关应用
 */
@SpringCloudApplication
public class QlpGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(QlpGatewayApplication.class, args);
    }
}
