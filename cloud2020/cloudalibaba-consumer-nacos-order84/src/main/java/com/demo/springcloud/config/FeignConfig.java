package com.demo.springcloud.config;

import com.demo.springcloud.entities.CommonResult;
import com.demo.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloudalibaba-paymentsentinel-provider",fallback = FeignConfigService.class)
public interface FeignConfig {

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id")Long id);
}
