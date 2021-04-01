package com.demo.springcloud.config;

import com.demo.springcloud.entities.CommonResult;
import com.demo.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author lx
 * @date 2021/3/30 10:12
 */
@Component
public class FeignConfigService implements FeignConfig {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444,"服务降级,返回--FeignConfigService");
    }
}
