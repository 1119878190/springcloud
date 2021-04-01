package com.demo.springcloud.feignConfig;

import cn.hutool.core.util.IdUtil;
import org.springframework.web.bind.annotation.PathVariable;

public interface PaymentService {

    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);

    public String paymentCircuitBreaker(@PathVariable("id")Integer id);

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id);
}
