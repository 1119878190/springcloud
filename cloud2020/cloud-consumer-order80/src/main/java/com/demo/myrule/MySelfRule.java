package com.demo.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lx
 * @date 2021/3/9 17:59
 */
@Configuration
public class MySelfRule {

    //修改ribbon的访问规则为随机,默认为轮训
    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
