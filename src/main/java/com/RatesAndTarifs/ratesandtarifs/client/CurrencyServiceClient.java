package com.RatesAndTarifs.ratesandtarifs.client;

import com.RatesAndTarifs.ratesandtarifs.DTO.CurrencyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-service", url = "${currency-service.url}")
public interface CurrencyServiceClient {

    @GetMapping("/currencies/{code}")
    CurrencyDTO getCurrencyInfo(@PathVariable("code") String code);
}
