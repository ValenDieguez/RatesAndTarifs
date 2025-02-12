package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.PricesDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface PricesService {

    PricesDTO getOne(LocalDateTime time, Long productId, Long brandId);
}
