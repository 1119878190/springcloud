package com.demo.springcloud.feign.impl;

import com.demo.springcloud.dao.PaymentDao;
import com.demo.springcloud.entities.Payment;
import com.demo.springcloud.feign.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lx
 * @date 2021/3/8 16:21
 */
@Service
public class PaymentServiceImpl implements PaymentService {


    @Autowired
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
