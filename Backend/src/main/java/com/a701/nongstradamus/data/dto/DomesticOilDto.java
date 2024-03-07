package com.a701.nongstradamus.data.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import lombok.*;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class DomesticOilDto {

    private Integer domesticOilPriceId;
    private Double dieselPrice;
    private Double kerosenePrice;
    private Double gasolinePrice;
    private LocalDate date;
}
