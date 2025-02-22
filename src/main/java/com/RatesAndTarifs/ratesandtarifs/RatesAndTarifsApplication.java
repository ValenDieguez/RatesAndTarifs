package com.RatesAndTarifs.ratesandtarifs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RatesAndTarifsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RatesAndTarifsApplication.class, args);
    }
}
