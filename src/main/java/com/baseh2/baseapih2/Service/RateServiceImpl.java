package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.RateDTO;
import com.baseh2.baseapih2.Repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class RateServiceImpl implements RateService {

    @Autowired
    RateRepository pricesRepository;

    @Override
    public RateDTO getOne(LocalDateTime time, Long productId, Long brandId) {
        List<RateDTO> pricesList = pricesRepository.findByBrandIdAndProductId(brandId, productId);
        if (pricesList.isEmpty()) {
            throw new NoSuchElementException("No hay precios para el brand y el producto proporcionado.");
        }

        return pricesList.stream()
                .filter(price -> price.getStartDate().isBefore(time) && price.getEndDate().isAfter(time))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No hay precio valido para la fecha dada."));
    }
}
