package com.a701.nongstradamus.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private String name;
    private String nickname;
    private String unit;
    private Long priceToday;
    private Long priceTomorrow;
    private Double ratio;
    private Integer rank;
}
