package springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.atguigu.springcloud.entities.CommentResult;
import com.atguigu.springcloud.entities.Payment;

@RestController
@Slf4j
public class OrderController {
    private final static String  url="http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommentResult<Payment> create(Payment payment){
        return restTemplate.postForObject(url+"/payment/create",payment,CommentResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommentResult<Payment> getPayment(@PathVariable("id")Long id){
        return restTemplate.getForObject(url+"/payment/get/"+id,CommentResult.class);
    }
    @GetMapping("/consumer/payment/zipkin")
    public String getPaymentZipkin(){
        return restTemplate.getForObject(url+"/payment/zipkin/",String.class);
    }
}
