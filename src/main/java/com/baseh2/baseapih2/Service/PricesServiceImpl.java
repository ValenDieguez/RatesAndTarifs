package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.PricesDTO;
import com.baseh2.baseapih2.Repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PricesServiceImpl implements PricesService {

    @Autowired
    PricesRepository pricesRepository;

    @Override
    public PricesDTO getOne(Integer id) {
        return pricesRepository.getOne(id);
    }
}
