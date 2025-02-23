package com.RatesAndTarifs.ratesandtarifs.controller;

import com.RatesAndTarifs.ratesandtarifs.DTO.RateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getRateById_ExistingRate_ReturnsRate() throws Exception {
        mockMvc.perform(get("/api/rates/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.price").value(1550));
    }

    @Test
    public void getRateById_NonExistingRate_Returns404() throws Exception {
        mockMvc.perform(get("/api/rates/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void searchRate_ExistingCriteria_ReturnsRate() throws Exception {
        String searchDate = "2022-01-01T12:00:00";
        mockMvc.perform(get("/api/rates/search")
                .param("date", searchDate)
                .param("productId", "1")
                .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.productId").value(1));
    }

    @Test
    public void createRate_ValidRate_ReturnsCreatedRate() throws Exception {
        RateDTO newRate = new RateDTO();
        newRate.setBrandId(1L);
        newRate.setProductId(1L);
        newRate.setPrice(2000);
        newRate.setCurrency("EUR");
        newRate.setStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        newRate.setEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));

        MvcResult result = mockMvc.perform(post("/api/rates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newRate)))
                .andExpect(status().isCreated())
                .andReturn();

        RateDTO createdRate = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                RateDTO.class);
        assertNotNull(createdRate.getId());
        assertEquals(newRate.getBrandId(), createdRate.getBrandId());
        assertEquals(newRate.getPrice(), createdRate.getPrice());
    }

    @Test
    public void updatePrice_ExistingRate_ReturnsUpdatedRate() throws Exception {
        Integer newPrice = 2000;
        mockMvc.perform(patch("/api/rates/1/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPrice)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.price").value(newPrice));
    }

    @Test
    public void updatePrice_NonExistingRate_Returns404() throws Exception {
        Integer newPrice = 2000;
        mockMvc.perform(patch("/api/rates/999/price")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPrice)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteRate_ExistingRate_Returns204() throws Exception {
        mockMvc.perform(delete("/api/rates/1"))
                .andExpect(status().isNoContent());

        // Verify the rate is deleted
        mockMvc.perform(get("/api/rates/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteRate_NonExistingRate_Returns404() throws Exception {
        mockMvc.perform(delete("/api/rates/999"))
                .andExpect(status().isNotFound());
    }
}