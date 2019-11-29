package com.qlp.cloud.provider.oa;

import com.qlp.cloud.common.security.annotation.EnableQlpFeignClients;
import com.qlp.cloud.common.security.annotation.EnableQlpResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@EnableQlpResourceServer
@EnableQlpFeignClients
@SpringCloudApplication
public class QlpOAApplication {

    public static void main(String[] args) {
        SpringApplication.run(QlpOAApplication.class, args);
    }
}
