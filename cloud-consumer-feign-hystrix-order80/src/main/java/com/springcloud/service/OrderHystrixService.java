package com.springcloud.service;

import com.springcloud.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * 该降级处理的信息是客户端调用服务端的降级
 *  测试方法为:shutdown服务器
 * */
@FeignClient(value = "CLOUD-PORVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)
public interface OrderHystrixService {
    @GetMapping("payment/hystrix/ok/{id}")
     String paymentInfo_ok(@PathVariable("id") Integer id);

    @GetMapping("payment/hystrix/timeout/{id}")
     String paymentInfo_timeout(@PathVariable("id")Integer id);
}
