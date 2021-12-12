package com.springcloud.service.impl;

import com.springcloud.service.OrderHystrixService;
import org.springframework.stereotype.Component;

/*hystrix降級*/
@Component
public class PaymentFallbackService implements OrderHystrixService {
    @Override
    public String paymentInfo_ok(Integer id) {
        return "---PaymentFallbackService fall back paymentInfo_ok,/(ㄒoㄒ)/~~ ";
    }

    @Override
    public String paymentInfo_timeout(Integer id) {
        return "---PaymentFallbackService fall back paymentInfo_timeout,/(ㄒoㄒ)/~~ ";
    }
}
