package com.demo.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author lx
 * @date 2021/3/8 18:54
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced   //负载均衡 默认轮询
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
