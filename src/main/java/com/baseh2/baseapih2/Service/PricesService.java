package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.PricesDTO;
import org.springframework.stereotype.Service;

@Service
public interface PricesService {

    PricesDTO getOne(Integer id);
}
