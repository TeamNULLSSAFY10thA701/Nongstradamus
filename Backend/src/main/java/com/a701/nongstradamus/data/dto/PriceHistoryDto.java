package com.a701.nongstradamus.data.dto;

import com.a701.nongstradamus.data.entity.ProductEntity;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistoryDto {
    private Date date;
    private Long price;
    private Double ratio;
    private Integer grade;
    private ProductEntity product;
}
