package com.RatesAndTarifs.ratesandtarifs.Service;

import com.RatesAndTarifs.ratesandtarifs.DTO.CurrencyDTO;
import com.RatesAndTarifs.ratesandtarifs.DTO.RateDTO;
import com.RatesAndTarifs.ratesandtarifs.Repository.RateRepository;
import com.RatesAndTarifs.ratesandtarifs.client.CurrencyServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;
    private final CurrencyServiceClient currencyServiceClient;

    @Override
    public RateDTO getOne(LocalDateTime time, Long productId, Long brandId) {
        RateDTO rate = findRateOrThrow(time, productId, brandId);
        enrichRateWithCurrencyInfo(rate);
        return rate;
    }

    @Override
    public RateDTO createRate(RateDTO rate) {
        validateNewRate(rate);
        return rateRepository.save(rate);
    }

    @Override
    public RateDTO getRateById(Long id) {
        RateDTO rate = findByIdOrThrow(id);
        enrichRateWithCurrencyInfo(rate);
        return rate;
    }

    @Override
    public RateDTO updateRatePrice(Long id, Integer newPrice) {
        validatePrice(newPrice);
        RateDTO existingRate = findByIdOrThrow(id);
        existingRate.setPrice(newPrice);
        return rateRepository.save(existingRate);
    }

    @Override
    public void deleteRate(Long id) {
        if (!rateRepository.existsById(id)) {
            throw new NoSuchElementException("Rate not found with ID: " + id);
        }
        rateRepository.deleteById(id);
    }

    private RateDTO findRateOrThrow(LocalDateTime time, Long productId, Long brandId) {
        return rateRepository.findCurrentRate(brandId, productId, time)
                .orElseThrow(() -> new NoSuchElementException("No valid rate found for the given date."));
    }

    private RateDTO findByIdOrThrow(Long id) {
        return rateRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Rate not found with ID: " + id));
    }

    private void validateNewRate(RateDTO rate) {
        validateBasicRateData(rate);
        validateNoOverlappingRates(rate);
    }

    private void validateBasicRateData(RateDTO rate) {
        validateDates(rate);
        validatePrice(rate.getPrice());
        validateCurrency(rate.getCurrency());
    }

    private void validateDates(RateDTO rate) {
        if (rate.getStartDate() == null || rate.getEndDate() == null) {
            throw new IllegalArgumentException("Start and end dates are required");
        }
        if (rate.getStartDate().isAfter(rate.getEndDate())) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }

    private void validatePrice(Integer price) {
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
    }

    private void validateCurrency(String currency) {
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency code is required");
        }
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

    private void enrichRateWithCurrencyInfo(RateDTO rate) {
        try {
            CurrencyDTO currencyInfo = currencyServiceClient.getCurrencyInfo(rate.getCurrency());
            rate.setCurrencyInfo(currencyInfo);
            if (currencyInfo != null && currencyInfo.getDecimals() != null) {
                double formattedPrice = rate.getPrice() / Math.pow(10, currencyInfo.getDecimals());
                rate.setFormattedPrice(formattedPrice);
            }
        } catch (ResourceAccessException e) {
            log.error("Currency service is unavailable: {}", e.getMessage());
            setDefaultCurrencyInfo(rate);
        } catch (Exception e) {
            log.error("Unexpected error while fetching currency information for {}: {}",
                    rate.getCurrency(), e.getMessage());
            setDefaultCurrencyInfo(rate);
        }
    }

    private void setDefaultCurrencyInfo(RateDTO rate) {
        CurrencyDTO defaultCurrency = new CurrencyDTO(
            rate.getCurrency(),
            "Currency Service Unavailable",
            2
        );
        rate.setCurrencyInfo(defaultCurrency);
        rate.setFormattedPrice(rate.getPrice() / 100.0);
    }
}
