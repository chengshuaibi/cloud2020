package com.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.service.OrderHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
/*
* 全局降级
* */
@DefaultProperties(defaultFallback = "payment_Glovbal_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private OrderHystrixService orderHystrixService;

    @GetMapping("consumer/hystrix/ok/{id}")
    private String paymentInfo_ok(@PathVariable("id") Integer id){
        String result= orderHystrixService.paymentInfo_ok(id);
        log.info("******* result "+result);
        return result;
    }

    /*
     * 服务降级
     *  该降级处理的信息是客户端的降级
     * 测试方法是设置短时间的timeout
     * */
    /*特殊化定制的*/
/*    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            //超过三秒钟就会触发降级方法
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "2000")})*/
    @HystrixCommand
   @GetMapping("consumer/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id")Integer id){
        String result= orderHystrixService.paymentInfo_timeout(id);
        log.info("******* result "+result);
        return result;
    }
    public String paymentTimeOutFallbackMethod(Integer id){
        return "线程池"+Thread.currentThread().getName()+" 我是消费者80,支付服务繁忙请稍后重试, id="+id+ "\t"+"o(╥﹏╥)o";
    }
    /*
    * 全局fallback方法
    * */
    public String payment_Glovbal_FallbackMethod(){
        return "global异常处理信息,请稍后重试";
    }

}
