package com.a701.nongstradamus.domain;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DomesticOilDto {

    private Integer domesticOilPriceId;
    private Integer avgPrice;
    private LocalDate date;
}
