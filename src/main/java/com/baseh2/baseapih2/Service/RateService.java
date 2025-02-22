package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.RateDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface RateService {

    RateDTO getOne(LocalDateTime time, Long productId, Long brandId);
}
