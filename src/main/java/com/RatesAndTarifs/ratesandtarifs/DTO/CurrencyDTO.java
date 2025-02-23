package com.RatesAndTarifs.ratesandtarifs.DTO;

import lombok.Data;

@Data
public class CurrencyDTO {
    private String code;
    private String name;
    private Integer decimals;

    public CurrencyDTO(String code, String name, Integer decimals) {
        this.code = code;
        this.name = name;
        this.decimals = decimals;
    }

    public CurrencyDTO() {
    }
}
