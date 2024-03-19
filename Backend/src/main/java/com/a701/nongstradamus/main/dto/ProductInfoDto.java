package com.a701.nongstradamus.main.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfoDto {
    private String name;
    private Integer price;
    private Double ratio;
}
