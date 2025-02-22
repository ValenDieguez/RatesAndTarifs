package com.baseh2.baseapih2.DTO;

import lombok.Data;

@Data
public class CurrencyDTO {
    private String code;
    private String name;
    private Integer decimals;
}