package com.demo.springcloud.controller;

import com.demo.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lx
 * @date 2021/3/21 16:33
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;


    @GetMapping("/sendMessage")
    public String sendMessage(){
        return messageProvider.send();
    }
}
