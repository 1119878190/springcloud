package com.demo.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.demo.springcloud.config.FeignConfig;
import com.demo.springcloud.config.FeignConfigService;
import com.demo.springcloud.entities.CommonResult;
import com.demo.springcloud.entities.Payment;
import io.micrometer.core.instrument.Meter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.ResultSet;

/**
 * @author lx
 * @date 2021/3/29 21:29
 */
@RestController
@Slf4j
public class CircleBreakerController {

    private static final String SERVICE_URL = "http://cloudalibaba-paymentsentinel-provider";


    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private FeignConfig feignConfig;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")//没有设置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback")//fallback只负责java业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler")//blockHandler只负责sentinel控制台配置违规
    //@SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler")//如果两个错误都触发,则blockhandler优先
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",
                        exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable("id")Long id){
        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

        if (id == 4){
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常......");
        }else  if(result.getData() == null){
            throw new NullPointerException("NullPointerException,空指针异常");
        }

        return result;
    }

    //本列是fallback
    public CommonResult handlerFallback(@PathVariable("id")Long id,Throwable e){
        Payment payment = new Payment(id,"null");
        return new CommonResult(4444,"兜底异常handlerFallback"+e.getMessage(),payment);
    }

    //本列是blockhandler
    public CommonResult blockHandler(@PathVariable("id")Long id, BlockException blockException){
        Payment payment = new Payment(id, "null");
        return new CommonResult(4444,"blockHandler-sentinel限流"+blockException.getMessage(),payment);

    }

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id){
        return feignConfig.paymentSQL(id);
    }
}
