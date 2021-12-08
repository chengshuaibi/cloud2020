package springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.atguigu.springcloud.entities.CommentResult;
import com.atguigu.springcloud.entities.Payment;

@RestController
@Slf4j
public class OrderController {
    private final static String  payment_url="http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommentResult<Payment> create(Payment payment){
        return restTemplate.postForObject(payment_url+"/payment/create",payment,CommentResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommentResult<Payment> getPayment(@PathVariable("id")Long id){
        return restTemplate.getForObject(payment_url+"/payment/get/"+id,CommentResult.class);
    }
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommentResult<Payment> payment2(@PathVariable("id") int id){
        ResponseEntity<CommentResult> forEntity = restTemplate.getForEntity(payment_url + "/payment/get/" + id, CommentResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()){
            return forEntity.getBody();
        }else{
            return new CommentResult<Payment>(444,"服务报错");
        }
    };
}
