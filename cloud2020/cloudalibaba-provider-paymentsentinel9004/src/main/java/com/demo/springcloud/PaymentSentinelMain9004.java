package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lx
 * @date 2021/3/29 21:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentSentinelMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentSentinelMain9004.class,args);
    }
}
