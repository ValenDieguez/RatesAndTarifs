package com.RatesAndTarifs.ratesandtarifs.Service;

import com.RatesAndTarifs.ratesandtarifs.DTO.RateDTO;

import java.time.LocalDateTime;

public interface RateService {
    RateDTO getOne(LocalDateTime time, Long productId, Long brandId);

    RateDTO getRateById(Long id);

    RateDTO createRate(RateDTO rate);

    RateDTO updateRatePrice(Long id, Integer newPrice);

    void deleteRate(Long id);
}
