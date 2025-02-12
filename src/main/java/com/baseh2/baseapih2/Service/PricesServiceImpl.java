package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.PricesDTO;
import com.baseh2.baseapih2.Repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
public class PricesServiceImpl implements PricesService {

    @Autowired
    PricesRepository pricesRepository;

    @Override
    public PricesDTO getOne(LocalDateTime time, Long productId, Long brandId) {

        List<PricesDTO> pricesList = pricesRepository.findByBrandIdAndProductId(brandId, productId);
        return pricesList.stream()
                .filter(price -> price.getStartDate().isBefore(time) && price.getEndDate().isAfter(time))
                .max(Comparator.comparing(PricesDTO::getPriority))
                .orElse(null);
    }
}
