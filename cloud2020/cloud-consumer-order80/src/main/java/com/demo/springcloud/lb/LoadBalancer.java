package com.demo.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自定义轮训算法
 */
public interface LoadBalancer {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);

}


