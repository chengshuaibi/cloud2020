package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommentResult;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class orderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value="/payment/get/{id}")
    CommentResult getElementById(@PathVariable("id") Long id){
        CommentResult elementById = paymentFeignService.getElementById(id);
        return elementById;

    }
}
