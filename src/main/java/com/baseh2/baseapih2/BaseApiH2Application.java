package com.baseh2.baseapih2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.baseh2.baseapih2")
public class BaseApiH2Application {
    public static void main(String[] args) {
        SpringApplication.run(BaseApiH2Application.class, args);
    }
}
