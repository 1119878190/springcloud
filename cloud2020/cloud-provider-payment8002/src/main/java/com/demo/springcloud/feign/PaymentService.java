package com.demo.springcloud.feign;

import com.demo.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author lx
 * @date 2021/3/8 16:19
 */
public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id")Long id);

}
