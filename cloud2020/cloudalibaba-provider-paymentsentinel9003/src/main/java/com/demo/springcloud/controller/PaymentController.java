package com.demo.springcloud.controller;

import com.demo.springcloud.entities.CommonResult;
import com.demo.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author lx
 * @date 2021/3/29 21:07
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;


    public static HashMap<Long, Payment> hashMap = new HashMap<>();
    static {
        hashMap.put(1L,new Payment(1L,"84268494865198534565"));
        hashMap.put(2L,new Payment(2L,"6156849826244526922656"));
        hashMap.put(3L,new Payment(3L,"31526989562624986256489"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable(value = "id")Long id){
        Payment payment = hashMap.get(id);
        CommonResult<Payment> result = new CommonResult<>(200,"from mysql,serverPort"+serverPort,payment);
        return result;
    }
}
