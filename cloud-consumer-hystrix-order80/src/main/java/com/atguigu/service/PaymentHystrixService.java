package com.atguigu.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("CLOUD-PAYMENT-HYSTRIX-SERVICE")
public interface PaymentHystrixService {

    @GetMapping("/hystrix/timeout/{id}")
    public String timeout(@PathVariable("id")Integer id);

    @GetMapping("/hystrix/CircleBreaker/{id}")
    public String circleBreaker(@PathVariable("id")Integer id);
}
