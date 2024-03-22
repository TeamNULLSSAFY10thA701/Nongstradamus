package com.a701.nongstradamus.price.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceTableDto {
    private String name;
    private String nickname;
    private String unit;
    private Long present;
    private Long future;
    private Double ratio;

    public void setRatio(){
        if(future != null && present != null)
            this.ratio = (future - present) / (double) present;
    }
}
