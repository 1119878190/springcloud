package com.demo.springcloud.config;

/**
 * @author lx
 * @date 2021/3/13 23:13
 *
 * feign 日志配置
 */

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class FeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
