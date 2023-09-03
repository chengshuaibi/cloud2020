package com.atuguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.util.function.Supplier;

@SpringBootApplication
@EnableEurekaClient
public class StreamMqMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMqMain8801.class);
    }

}
