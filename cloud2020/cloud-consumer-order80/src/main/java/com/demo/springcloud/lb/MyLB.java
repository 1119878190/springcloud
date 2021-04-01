package com.demo.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lx
 * @date 2021/3/13 16:36
 */
@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAadIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current >= 2147473647 ? 0 : current+1;

        }while(!this.atomicInteger.compareAndSet(current,next));
        System.out.println("----------第几次访问,次数next:"+next);
        return next;
    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        //获取实例的数量并取余计算下标
        int index = getAadIncrement() % serviceInstances.size();
        return serviceInstances.get(index);//获取到了那个服务的下标
    }
}
