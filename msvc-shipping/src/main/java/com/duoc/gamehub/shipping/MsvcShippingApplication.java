package com.duoc.gamehub.shipping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcShippingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsvcShippingApplication.class, args);
    }
}