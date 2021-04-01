package com.demo.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.demo.springcloud.feignConfig.FeignConfig;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author lx
 * @date 2021/3/14 16:13
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Autowired
    private FeignConfig feignConfig;

    /**
     * 正常
     * @param id
     * @return
     */
    @GetMapping(value = "/consumer/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = feignConfig.paymentInfo_OK(id);
        return result;
    }


    /**
     * 超时
     * 如果1.5秒内该方法没有响应完,则调用兜底方法
     * @param id
     * @return
     */
    @GetMapping("/consumer/hystrix/timeout/{id}")
    //@HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")})
    @HystrixCommand   //这里没有指定降级后的方法,则调用全局降级方法
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        int a = 10/0;
        String result = feignConfig.paymentInfo_TimeOut(id);
        return result;
    }

    /**
     * 兜底方法
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id){
        return "我是消费者80,对方支付系统繁忙,请10秒钟后再次尝试T_T";
    }

    //下面是全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息,请稍后再试";
    }



}
