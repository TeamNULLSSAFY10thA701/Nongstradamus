package com.a701.nongstradamus.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@Entity
@Builder
@Getter
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

    public DomesticOilEntity() {
    }

    @Builder
    public DomesticOilEntity(Integer domesticOilPriceId, Double dieselPrice, Double kerosenePrice, Double gasolinePrice, LocalDate date) {
        this.domesticOilPriceId = domesticOilPriceId;
        this.dieselPrice = dieselPrice;
        this.kerosenePrice = kerosenePrice;
        this.gasolinePrice = gasolinePrice;
        this.date = date;
    }
}
