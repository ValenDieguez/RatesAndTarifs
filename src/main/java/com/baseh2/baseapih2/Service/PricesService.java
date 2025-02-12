package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.PricesDTO;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public interface PricesService {

    PricesDTO getOne( Timestamp time,  Long productId,  Long brandId);
}
