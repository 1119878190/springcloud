package com.demo.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 * @date 2021/3/20 17:20
 *
 * curl -X POST "http://localhost:3355/actuator/refresh"  手动版自动刷新需要提交post请求
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")  //这个值实在github仓库里的  通过3344读取
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }

}
