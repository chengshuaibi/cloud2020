package springcloud.service;

import com.atguigu.springcloud.entities.CommentResult;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE",configuration = RoundRobinRule.class)
public interface PaymentFeignService {
    @GetMapping(value="/payment/get/{id}")
    public CommentResult getElementById(@PathVariable("id") Long id);
}
