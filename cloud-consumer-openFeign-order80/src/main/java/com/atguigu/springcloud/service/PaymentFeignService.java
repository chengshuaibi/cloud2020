package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommentResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping(value="/payment/get/{id}")
     CommentResult getElementById(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/timeout")
     String paymentFeignTimeout();
}
