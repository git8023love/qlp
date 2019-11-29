package com.qlp.cloud.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 监控中心
 */
@EnableAdminServer
@SpringBootApplication
public class QlpMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(QlpMonitorApplication.class, args);
    }
}
