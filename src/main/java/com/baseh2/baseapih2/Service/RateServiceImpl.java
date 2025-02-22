package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.RateDTO;
import com.baseh2.baseapih2.DTO.CurrencyDTO;
import com.baseh2.baseapih2.Repository.RateRepository;
import com.baseh2.baseapih2.client.CurrencyServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private CurrencyServiceClient currencyServiceClient;

    @Override
    public RateDTO getOne(LocalDateTime time, Long productId, Long brandId) {
        RateDTO rate = rateRepository.findCurrentRate(brandId, productId, time)
                .orElseThrow(() -> new NoSuchElementException("No valid rate found for the given date."));

        enrichRateWithCurrencyInfo(rate);
        return rate;
    }

    @Override
    public RateDTO createRate(RateDTO rate) {
        validateRate(rate);
        validateNoOverlappingRates(rate);
        return rateRepository.save(rate);
    }

    private void validateNoOverlappingRates(RateDTO rate) {
        List<RateDTO> overlappingRates = rateRepository.findOverlappingRates(
                rate.getBrandId(),
                rate.getProductId(),
                rate.getStartDate(),
                rate.getEndDate()
        );

        if (!overlappingRates.isEmpty()) {
            throw new IllegalArgumentException("There is already a rate defined for this period");
        }
    }
    @Override
    public RateDTO getRateById(Long id) {
        RateDTO rate = rateRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rate not found with ID: " + id));

        enrichRateWithCurrencyInfo(rate);
        return rate;
    }

    @Override
    public RateDTO updateRatePrice(Long id, Integer newPrice) {
        RateDTO existingRate = rateRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarifa no encontrada con ID: " + id));

        existingRate.setPrice(newPrice);
        return rateRepository.save(existingRate);
    }

    @Override
    public void deleteRate(Long id) {
        if (!rateRepository.existsById(id)) {
            throw new NoSuchElementException("Tarifa no encontrada con ID: " + id);
        }
        rateRepository.deleteById(id);
    }

    private void enrichRateWithCurrencyInfo(RateDTO rate) {
        try {
            CurrencyDTO currencyInfo = currencyServiceClient.getCurrencyInfo(rate.getCurrency());
            rate.setCurrencyInfo(currencyInfo);
            if (currencyInfo != null && currencyInfo.getDecimals() != null) {
                double formattedPrice = rate.getPrice() / Math.pow(10, currencyInfo.getDecimals());
                rate.setFormattedPrice(formattedPrice);
            }
        } catch (Exception e) {
        }
    }

    private void validateRate(RateDTO rate) {
        if (rate.getStartDate() == null || rate.getEndDate() == null) {
            throw new IllegalArgumentException("Start and end dates are required");
        }
        if (rate.getStartDate().isAfter(rate.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        if (rate.getPrice() == null || rate.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        if (rate.getCurrency() == null || rate.getCurrency().trim().isEmpty()) {
            throw new IllegalArgumentException("Currency code is required");
        }
    }
}
