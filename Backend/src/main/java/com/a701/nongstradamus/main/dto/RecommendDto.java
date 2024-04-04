package com.a701.nongstradamus.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendDto {
    private Long priceToday;
    private Long priceTomorrow;
    private String name;
    private String nickname;
    private String unit;
    private Integer mention;
}
