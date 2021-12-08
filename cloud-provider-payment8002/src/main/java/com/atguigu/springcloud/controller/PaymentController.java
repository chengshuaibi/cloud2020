package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommentResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/create",method = RequestMethod.POST)
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
            return new CommentResult(200,"查询数据库成功sevrerPort: ："+serverPort,result);
        }else {
            return new CommentResult(444,"没有对应记录",null);
        }
    }
}
