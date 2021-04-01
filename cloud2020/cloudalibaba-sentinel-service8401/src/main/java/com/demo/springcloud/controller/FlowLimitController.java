package com.demo.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author lx
 * @date 2021/3/29 15:35
 */
@RestController
public class FlowLimitController {


    @GetMapping("/testa")
    public String testA(){
        return "-----------aaaaa";
    }

    @GetMapping("/testb")
    public String testB(){
        return "-----------bbbbb";
    }

    @GetMapping("/testd")
    public String testD(){
        int age = 10/0;
        return "-----------ddddd";
    }

    @GetMapping("/teste")
    public String testE(){
        int age = 10/0;
        return "-----------eeeee";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false)String p1,
                             @RequestParam(value = "p2",required = false)String p2){
        return "-------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception){
        return "-------deal_testHotKey,T_T";
    }
}
