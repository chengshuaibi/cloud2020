package com.atguigu.controller;

import com.atguigu.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;
    @GetMapping("/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties = {
                    //调用超过3秒调用fallback
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
            })
    public String timeout(@PathVariable("id")Integer id){
        return paymentHystrixService.timeout(id);
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "order80调用了"+"paymentInfo_TimeOutHandler"+id;

    }

}
