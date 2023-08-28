package com.atguigu.controller;

import com.atguigu.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id")Integer id){
        return paymentService.paymentInfo_TimeOut(id);
    }
}
