package com.a701.nongstradamus.data.dto;

import com.fasterxml.jackson.databind.JsonNode;
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
public class GlobalOilDto {

    private Integer globalOilPriceId;
    private Double wtiPrice;
    private Double brentPrice;
    private Double dxlPrice;
    private Double gasolinePrice;
    private LocalDate date;
}
