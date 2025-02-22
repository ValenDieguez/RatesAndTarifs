package com.baseh2.baseapih2.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.baseh2.baseapih2.client")
public class FeignConfig {
}