package com.baseh2.baseapih2.service;

import com.baseh2.baseapih2.DTO.RateDTO;
import com.baseh2.baseapih2.Repository.RateRepository;
import com.baseh2.baseapih2.Service.RateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RateServiceImplTest {

    @Mock
    private RateRepository rateRepository;

    @InjectMocks
    private RateServiceImpl rateService;

    private RateDTO testRate;

    @BeforeEach
    void setUp() {
        testRate = new RateDTO();
        testRate.setId(1L);
        testRate.setBrandId(1L);
        testRate.setProductId(35455L);
        testRate.setPrice(3550);
        testRate.setCurrency("EUR");
        testRate.setStartDate(LocalDateTime.parse("2022-01-01T00:00:00"));
        testRate.setEndDate(LocalDateTime.parse("2022-12-31T23:59:59"));
    }

    @Test
    void whenValidRequest_thenReturnRate() {
        // Given
        LocalDateTime requestDate = LocalDateTime.parse("2022-06-14T10:00:00");
        when(rateRepository.findByBrandIdAndProductId(1L, 35455L))
                .thenReturn(Arrays.asList(testRate));

        // When
        RateDTO result = rateService.getOne(requestDate, 35455L, 1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(3550, result.getPrice());
    }

    @Test
    void whenNoRatesFound_thenThrowException() {
        // Given
        LocalDateTime requestDate = LocalDateTime.parse("2022-06-14T10:00:00");
        when(rateRepository.findByBrandIdAndProductId(1L, 35455L))
                .thenReturn(Collections.emptyList());

        // Then
        assertThrows(NoSuchElementException.class, () -> {
            rateService.getOne(requestDate, 35455L, 1L);
        });
    }

    @Test
    void whenDateOutOfRange_thenThrowException() {
        // Given
        LocalDateTime requestDate = LocalDateTime.parse("2023-06-14T10:00:00");
        when(rateRepository.findByBrandIdAndProductId(1L, 35455L))
                .thenReturn(Arrays.asList(testRate));

        // Then
        assertThrows(NoSuchElementException.class, () -> {
            rateService.getOne(requestDate, 35455L, 1L);
        });
    }
}
