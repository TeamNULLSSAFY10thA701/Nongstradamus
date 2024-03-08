package com.a701.nongstradamus.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DomesticOilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer domesticOilPriceId;
    @Column
    private Double dieselPrice;
    @Column
    private Double kerosenePrice;
    @Column
    private Double gasolinePrice;
    @Column
    private LocalDate date;

}
