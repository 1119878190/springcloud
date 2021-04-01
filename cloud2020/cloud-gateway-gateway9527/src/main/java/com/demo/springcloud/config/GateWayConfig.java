package com.demo.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lx
 * @date 2021/3/19 21:12
 *
 * 除了在yml文件中配置路由,还可以以代码的形式配置
 *当访问 localhost:9527/guonei   时 访问http://news.baidu.com/guoei
 */
@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_route_atguigu",r -> r.path("/guonei").uri("http://news.baidu.com/guoei")).build();

        return routes.build();


    }
}
