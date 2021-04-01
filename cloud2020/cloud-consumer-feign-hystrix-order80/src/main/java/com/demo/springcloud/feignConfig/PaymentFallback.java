package com.demo.springcloud.feignConfig;

import org.springframework.stereotype.Component;

/**
 * @author lx
 * @date 2021/3/14 18:55
 *
 * 这里实现了feign接口,只需要在@FeignClient中设置fallback的实现类,就可以不用谢兜底方法
 */
@Component
public class PaymentFallback implements FeignConfig {

    @Override
    public String paymentInfo_OK(Integer id) {
        return "这是order80  PaymentFallback --- paymentInfo_OK";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "这是order80 PaymentFallback  --- paymentInfo_TimeOut ---";
    }
}
