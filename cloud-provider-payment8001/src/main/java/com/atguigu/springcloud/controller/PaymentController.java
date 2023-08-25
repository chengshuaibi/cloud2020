package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommentResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommentResult create(@RequestBody Payment payment){
        int result =paymentService.create(payment);
        log.info("*****插入结果为: "+result);
        if (result>0){
            return new CommentResult(200,"插入数据库成功,serverPort: ："+serverPort,result);
        }else {
            return new CommentResult(444,"插入数据库失败",null);
        }
    }
    @GetMapping(value="/payment/get/{id}")
    public CommentResult getElementById(@PathVariable("id") Long id){
        Payment result =paymentService.getElementById(id);
        if (result!=null){
            return new CommentResult(200,"查询数据库成功serverPort: ："+serverPort,result);
        }else {
            return new CommentResult(444,"没有对应记录",null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> objects =discoveryClient.getServices();
        for (String object : objects) {
            log.info(object);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"\t"+instance.getUri()+"\t"+instance.getHost()+"\t"+instance.getPort());
        }
        return this.discoveryClient;
    }
    @GetMapping("/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
