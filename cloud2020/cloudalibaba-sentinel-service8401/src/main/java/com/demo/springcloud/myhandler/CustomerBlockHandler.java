package com.demo.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.demo.springcloud.entities.CommonResult;
import com.demo.springcloud.entities.Payment;
import com.sun.deploy.security.BlockedDialog;

/**
 * @author lx
 * @date 2021/3/29 20:28
 */
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(444,"按照客户自定义,global handlerException------1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444,"按照客户自定义,global handlerException-------2");
    }
}
