package springcloud.controller;

import com.atguigu.springcloud.entities.CommentResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springcloud.service.PaymentFeignService;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id")Long id ){
        System.out.println(paymentFeignService.getElementById(id));
        CommentResult result = paymentFeignService.getElementById(id);
        return result;

    }
}
