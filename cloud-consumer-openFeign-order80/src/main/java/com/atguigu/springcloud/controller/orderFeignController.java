package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommentResult;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j

public class orderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;
    @Value("${server.port}")
    private String serverPort;
    @GetMapping(value="/payment/get/{id}")
    public CommentResult getElementById(@PathVariable("id") Long id){
        CommentResult elementById = paymentFeignService.getElementById(id);
        return elementById;
    }

    /*
    * openFeign超时接口控制
    *
    * */
    @GetMapping("consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
    return paymentFeignService.paymentFeignTimeout();
    }
}
