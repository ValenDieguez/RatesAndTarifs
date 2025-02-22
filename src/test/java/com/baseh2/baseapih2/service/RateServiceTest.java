package com.baseh2.baseapih2.service;

import com.baseh2.baseapih2.DTO.RateDTO;
import com.baseh2.baseapih2.DTO.CurrencyDTO;
import com.baseh2.baseapih2.Repository.RateRepository;
import com.baseh2.baseapih2.Service.RateService;
import com.baseh2.baseapih2.client.CurrencyServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RateServiceTest {

    @Mock
    private RateRepository rateRepository;

    @Mock
    private CurrencyServiceClient currencyServiceClient;

    @InjectMocks
    private RateService rateService;

    private RateDTO testRate;
    private CurrencyDTO testCurrency;

    @BeforeEach
    void setUp() {
        testRate = new RateDTO();
        testRate.setId(1L);
        testRate.setBrandId(1L);
        testRate.setProductId(1L);
        testRate.setPrice(1550);
        testRate.setCurrency("EUR");
        testRate.setStartDate(LocalDateTime.parse("2022-01-01T00:00:00"));
        testRate.setEndDate(LocalDateTime.parse("2022-05-31T23:59:59"));

        testCurrency = new CurrencyDTO();
        testCurrency.setCode("EUR");
        testCurrency.setName("Euro");
        testCurrency.setDecimals(2);
    }

    @Test
    void whenGetRateByValidParameters_thenReturnRate() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.parse("2022-01-15T10:00:00");
        Long productId = 1L;
        Long brandId = 1L;

        when(rateRepository.findByDateAndProductAndBrand(
            applicationDate, productId, brandId))
            .thenReturn(Optional.of(testRate));

        when(currencyServiceClient.getCurrencyInfo("EUR"))
            .thenReturn(testCurrency);

        // When
        RateDTO result = rateService.getRate(applicationDate, productId, brandId);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("EUR", result.getCurrency());
        assertEquals(1550, result.getPrice());
        assertNotNull(result.getCurrencyInfo());
        assertEquals(2, result.getCurrencyInfo().getDecimals());
    }

    @Test
    void whenGetRateWithInvalidParameters_thenReturnNull() {
        // Given
        LocalDateTime applicationDate = LocalDateTime.parse("2023-01-15T10:00:00");
        Long productId = 999L;
        Long brandId = 999L;

        when(rateRepository.findByDateAndProductAndBrand(
            applicationDate, productId, brandId))
            .thenReturn(Optional.empty());

        // When
        RateDTO result = rateService.getRate(applicationDate, productId, brandId);

        // Then
        assertNull(result);
    }
}
