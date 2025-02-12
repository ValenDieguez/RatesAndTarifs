package com.baseh2.baseapih2.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;


import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name="task")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PricesDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(name="BRAND_ID")
    private Long Brand;
    @Column(name="PRICE_LIST")
    private Integer priceList;
    @Column(name="PRODUCT_ID")
    private Long productID;
    @Column(name="PRIORITY")
    private Integer priority;
    @Column(name="PRICE")
    private Double price;
    @Column(name="CURRENCY")
    private String currency;
    @Column(name = "START_DATE")
    private Timestamp startDate;
}
