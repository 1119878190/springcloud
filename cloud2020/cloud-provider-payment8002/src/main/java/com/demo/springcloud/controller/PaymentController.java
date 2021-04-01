package com.demo.springcloud.controller;


import com.demo.springcloud.entities.CommonResult;
import com.demo.springcloud.entities.Payment;
import com.demo.springcloud.feign.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lx
 * @date 2021/3/8 16:23
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private PaymentService paymentService;


    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果:"+result);
        if (result >0){
            return new CommonResult(200,"插入数据成功,serverPort:"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }


    @GetMapping(value = "/payment/get/{id}")
    public CommonResult selectPaymentById(@PathVariable("id")Long id){
       Payment payment = paymentService.getPaymentById(id);
       log.info("*****查询结果"+payment);

       if (payment != null){
           return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
       }else {
           return new CommonResult(444,"没有对应记录"+id,null);
       }
    }

    @GetMapping("/payment/lb")
    public String getPayment(){
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return " hi i am aymentzipkin server fall back ,welcome to my world 8002 ^_^";
    }
}
