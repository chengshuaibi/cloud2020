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

    /*
    * 服务熔断
    * */
    @HystrixCommand(fallbackMethod = "paymentCircleBreaker_TimeOutHandler",
            commandProperties = {
                    @HystrixProperty(name="circleBreaker.enable",value = "true"),//是否开启断路器
                    @HystrixProperty(name="circleBreaker.requestVolumeThreshold",value = "10"),//请求次数
                    @HystrixProperty(name="circleBreaker.sleepWindowsMillisecond",value = "10000"),//时间窗口期
                    @HystrixProperty(name="circleBreaker.errorThresholdPercentTage",value = "60"),//失败率到达多少跳闸

            })
    public String paymentInfo_CircleBreaker(Integer id){
     if (id<0){
         throw new RuntimeException("id不能为负数");
     }
        return "线程池"+Thread.currentThread().getName()+"paymentInfo_CircleBreaker"+id+"调用成功";
    }

    public String paymentCircleBreaker_TimeOutHandler(Integer id) {
        return "调用了"+"paymentInfo_CircleBreaker"+"id不能为负数";

    }
    }
