package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.RateDTO;
import java.time.LocalDateTime;

public interface RateService {
    RateDTO getOne(LocalDateTime time, Long productId, Long brandId);
    RateDTO getRateById(Long id);
    RateDTO createRate(RateDTO rate);
    RateDTO updateRatePrice(Long id, Integer newPrice);
    void deleteRate(Long id);
}
