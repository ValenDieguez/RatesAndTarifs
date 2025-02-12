package com.baseh2.baseapih2;

import com.baseh2.baseapih2.DTO.PricesDTO;
import com.baseh2.baseapih2.Repository.PricesRepository;
import com.baseh2.baseapih2.Service.PricesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PricesServiceTest {

    @InjectMocks
    private PricesServiceImpl pricesService;

    @Mock
    private PricesRepository pricesRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrecioInicial() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 10, 0);  // Ajusta la fecha si es necesario
        Long productId = 35455L;
        Long brandId = 1L;

        List<PricesDTO> mockPrices = createPricesMockList();

        when(pricesRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(mockPrices);

        PricesDTO result = pricesService.getOne(requestTime, productId, brandId);

        assertNotNull(result);
        assertEquals(35.50, result.getPrice());
    }

    @Test
    void testOferta14Mediodia() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 16, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        List<PricesDTO> mockPrices = createPricesMockList();

        when(pricesRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(mockPrices);

        PricesDTO result = pricesService.getOne(requestTime, productId, brandId);

        assertNotNull(result);
        assertEquals(25.45, result.getPrice());
    }

    @Test
    void testVueltaPrecioAnterior14() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 14, 21, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        List<PricesDTO> mockPrices = createPricesMockList();
        when(pricesRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(mockPrices);

        PricesDTO result = pricesService.getOne(requestTime, productId, brandId);

        assertNotNull(result);
        assertEquals(35.50, result.getPrice());
    }

    @Test
    void testOferta15Mañana() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 15, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        List<PricesDTO> mockPrices = createPricesMockList();
        when(pricesRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(mockPrices);

        PricesDTO result = pricesService.getOne(requestTime, productId, brandId);

        assertNotNull(result);
        assertEquals(30.50, result.getPrice());
    }

    @Test
    void testPrecioFinal16() {
        LocalDateTime requestTime = LocalDateTime.of(2020, 6, 16, 21, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        List<PricesDTO> mockPrices =createPricesMockList();
        when(pricesRepository.findByBrandIdAndProductId(brandId, productId)).thenReturn(mockPrices);

        PricesDTO result = pricesService.getOne(requestTime, productId, brandId);

        assertNotNull(result);
        assertEquals(38.95, result.getPrice());
    }

    private List<PricesDTO> createPricesMockList(){
        PricesDTO priceDTO1 = createPricesDTO(1L, 1L, 35455L,
                LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                0, 35.50);

        PricesDTO priceDTO2 = createPricesDTO(2L, 1L, 35455L,
                LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                1, 25.45);

        PricesDTO priceDTO3 = createPricesDTO(3L, 1L, 35455L,
                LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                1, 30.50);

        PricesDTO priceDTO4 = createPricesDTO(4L, 1L, 35455L,
                LocalDateTime.of(2020, 6, 15, 16, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1, 38.95);

        return List.of(priceDTO1, priceDTO2, priceDTO3, priceDTO4);
    }

    private PricesDTO createPricesDTO(Long id, Long brandId, Long productId,
                                      LocalDateTime startDate, LocalDateTime endDate,
                                      int priority, double price) {
        PricesDTO pricesDTO = new PricesDTO();
        pricesDTO.setId(id);
        pricesDTO.setBrandId(brandId);
        pricesDTO.setProductId(productId);
        pricesDTO.setStartDate(startDate);
        pricesDTO.setEndDate(endDate);
        pricesDTO.setPriority(priority);
        pricesDTO.setPrice(price);
        return pricesDTO;
    }
}
