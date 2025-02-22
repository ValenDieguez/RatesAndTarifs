package com.baseh2.baseapih2.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Entity
@Table(name="T_RATES")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RateDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(name="BRAND_ID")
    private Long brandId;
    @Column(name="PRICE")
    private Integer price;
    @Column(name="PRODUCT_ID")
    private Long productId;
    @Column(name="CURRENCY_CODE")
    private String currency;
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
}
