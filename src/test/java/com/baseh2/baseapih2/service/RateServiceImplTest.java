package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.RateDTO;
import com.baseh2.baseapih2.DTO.CurrencyDTO;
import com.baseh2.baseapih2.Repository.RateRepository;
import com.baseh2.baseapih2.client.CurrencyServiceClient;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RateServiceImplTest {

    @Mock
    private RateRepository rateRepository;

    @Mock
    private CurrencyServiceClient currencyServiceClient;

    @InjectMocks
    private RateServiceImpl rateService;

    private RateDTO testRate;
    private CurrencyDTO testCurrency;

    @BeforeEach
    public void setUp() {
        testRate = new RateDTO();
        testRate.setId(1L);
        testRate.setBrandId(1L);
        testRate.setProductId(35455L);
        testRate.setPrice(1550);
        testRate.setCurrency("EUR");
        testRate.setStartDate(LocalDateTime.of(2024, 1, 1, 0, 0));
        testRate.setEndDate(LocalDateTime.of(2024, 12, 31, 23, 59));

        testCurrency = new CurrencyDTO();
        testCurrency.setCode("EUR");
        testCurrency.setDecimals(2);
    }
    @Test
    public void getOne_WhenValidRequest_ShouldReturnRate() {
        LocalDateTime requestDate = LocalDateTime.of(2024, 6, 14, 10, 0);
        when(rateRepository.findCurrentRate(1L, 35455L, requestDate))
                .thenReturn(Optional.of(testRate));
        when(currencyServiceClient.getCurrencyInfo("EUR"))
                .thenReturn(testCurrency);

        RateDTO result = rateService.getOne(requestDate, 35455L, 1L);

        assertNotNull(result);
        assertEquals(testRate.getId(), result.getId());
        assertEquals(15.50, result.getFormattedPrice(), 0.001);

        verify(rateRepository).findCurrentRate(1L, 35455L, requestDate);
        verify(currencyServiceClient).getCurrencyInfo("EUR");
    }

    @Test
    public void getOne_WhenNoRatesFound_ShouldThrowException() {
        LocalDateTime requestDate = LocalDateTime.of(2024, 6, 14, 10, 0);
        when(rateRepository.findCurrentRate(1L, 35455L, requestDate))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
            rateService.getOne(requestDate, 35455L, 1L));
    }
    @Test
    public void getRateById_WhenRateDoesNotExist_ShouldThrowException() {
        when(rateRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
            rateService.getRateById(999L));
    }
    @Test
    public void createRate_WithInvalidData_ShouldThrowException() {
        testRate.setStartDate(null);

        assertThrows(IllegalArgumentException.class, () ->
            rateService.createRate(testRate));
    }
    @Test
    public void updateRatePrice_WhenRateDoesNotExist_ShouldThrowException() {
        when(rateRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () ->
            rateService.updateRatePrice(999L, 2000));
    }
    @Test
    public void deleteRate_WhenRateDoesNotExist_ShouldThrowException() {
        when(rateRepository.existsById(999L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () ->
            rateService.deleteRate(999L));
    }
    @Test
    public void validateRate_WithStartDateAfterEndDate_ShouldThrowException() {
        testRate.setStartDate(LocalDateTime.now().plusDays(2));
        testRate.setEndDate(LocalDateTime.now());

        assertThrows(IllegalArgumentException.class, () ->
            rateService.createRate(testRate));
    }
    @Test
    public void validateRate_WithZeroPrice_ShouldThrowException() {
        testRate.setPrice(0);

        assertThrows(IllegalArgumentException.class, () ->
            rateService.createRate(testRate));
    }
    @Test
    public void validateRate_WithEmptyCurrency_ShouldThrowException() {
        testRate.setCurrency("  ");

        assertThrows(IllegalArgumentException.class, () ->
            rateService.createRate(testRate));
    }
}
