package com.atguigu.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id){
    return "线程池"+Thread.currentThread().getName()+"paymentInfo_OK"+id+"\t"+"哈哈哈😊";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",
        commandProperties = {
            //调用超过3秒调用fallback
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
        })
    public String paymentInfo_TimeOut(Integer id){
            int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_TimeOut"+id+"\t"+"超时勒/(ㄒoㄒ)/~~"+"耗时 :"+(timeNumber)+"秒";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "调用了"+"paymentInfo_TimeOutHandler";

    }
    }
