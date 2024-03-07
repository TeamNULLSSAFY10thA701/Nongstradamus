package com.a701.nongstradamus.data.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import java.time.LocalDate;


@Data
@Entity
@Builder
@Getter
public class GlobalOilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer globalOilPriceId;
    @Column
    private Double wtiPrice;
    @Column
    private Double brentPrice;
    @Column
    private Double dxlPrice;
    @Column
    private Double gasolinePrice;
    @Column
    private LocalDate date;

    public GlobalOilEntity() {
    }

    @Builder
    public GlobalOilEntity(Integer globalOilPriceId, Double wtiPrice, Double brentPrice, Double dxlPrice, Double gasolinePrice, LocalDate date) {
        this.globalOilPriceId = globalOilPriceId;
        this.wtiPrice = wtiPrice;
        this.brentPrice = brentPrice;
        this.dxlPrice = dxlPrice;
        this.gasolinePrice = gasolinePrice;
        this.date = date;
    }
}
