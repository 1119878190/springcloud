package com.demo.springcloud.feignConfig;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author lx
 * @date 2021/3/14 15:30
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * 正常
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_OK,id:"+id+"\t"+"^_^";
    }

    /**
     * 超时
     * 如果3秒内该方法没有响应完,则调用兜底方法
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")})
    public String paymentInfo_TimeOut(Integer id){
        int time = 3;
        //int a = 10/0;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "线程池"+Thread.currentThread().getName()+"paymentInfo_TimeOut,id:"+id+"\t"+"呜呜呜"+"耗时秒钟";
    }

    /**
     * 兜底方法
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"系统系统忙,请稍后再试,id:"+id+"\t"+"我是兜底的---__——";
    }


    //==============服务熔断===================
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),//失败率达到多少后跳闸

    })
    @Override
    public String paymentCircuitBreaker(@PathVariable("id")Integer id){
        if (id < 0){
            throw new RuntimeException("***id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功,流水号:"+serialNumber;
    }

    @Override
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数,请稍后再试+id:"+id;
    }
}
