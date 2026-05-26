package com.duoc.gamehub.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcPromotionApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsvcPromotionApplication.class, args);
    }
}