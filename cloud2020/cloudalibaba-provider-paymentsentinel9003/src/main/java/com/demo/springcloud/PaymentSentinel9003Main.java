package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lx
 * @date 2021/3/29 21:06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentSentinel9003Main {
    public static void main(String[] args) {
        SpringApplication.run(PaymentSentinel9003Main.class,args);
    }
}
