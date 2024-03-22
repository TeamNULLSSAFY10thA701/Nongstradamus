package com.a701.nongstradamus.price.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceGraphDto {

    private Long price;

    private Double ratio;

}
