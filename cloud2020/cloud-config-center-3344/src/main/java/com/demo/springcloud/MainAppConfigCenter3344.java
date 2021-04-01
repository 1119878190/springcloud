package com.demo.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author lx
 * @date 2021/3/20 15:53
 * 一次修改,全部通知
 * curl -X POST "http://localhost:3344/actuator/bus-refresh" 总线发送该post请求,其它config 的client都刷新
 *
 *         3355------->
 *                     --------->3344---------->github
 *         3366------->            |
 *                                 |
 *                              rabbtimq
 *
 * curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"   只通知3355  微服务:端口号
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class MainAppConfigCenter3344 {
    public static void main(String[] args) {
        SpringApplication.run(MainAppConfigCenter3344.class,args);
    }
}
