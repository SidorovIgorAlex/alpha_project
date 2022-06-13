package com.alpha.test.application.feign.client.alpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AlphaApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlphaApplication.class, args);
    }
}
