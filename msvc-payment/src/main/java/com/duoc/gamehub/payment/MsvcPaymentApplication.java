package com.duoc.gamehub.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsvcPaymentApplication.class, args);
    }
}