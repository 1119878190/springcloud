package com.demo.springcloud.controller;

import com.demo.springcloud.entities.CommonResult;
import com.demo.springcloud.entities.Payment;
import com.demo.springcloud.feign.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lx
 * @date 2021/3/13 17:58   feign == ribbon + resttemplate
 */
@RestController
@Slf4j
public class OpenFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        System.out.println("这是openfeign");
        return paymentFeignService.getPaymentById(id);
    }

    /**
     * 这里让payment 服务端暂停3s,如果直接调用consumer的这个方法,会出错,因为openfeign的默认等待时间为1s,1s等不到就会报错
     * 可以在配置文件中修改默认的等待时间
     * ribbon:
     *   ReadTimeout: 5000
     *   ConnectTimeout: 5000
     * @return
     */
    @GetMapping("/consumer/payment/feign/timeout")
    public String timeOut(){
        return paymentFeignService.timeOut();
    }

    @GetMapping("/consumer/getzipkin")
    public String getZipkin(){
        return paymentFeignService.getZipkin();
    }
}
