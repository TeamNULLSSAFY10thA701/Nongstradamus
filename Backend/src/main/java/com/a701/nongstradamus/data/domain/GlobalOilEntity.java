package com.a701.nongstradamus.data.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;


@Data
@Entity
@Builder
@Getter
public class GlobalOilEntity {

    private static ModelMapper modelMapper;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer globalOilPriceId;
    @Column
    private Double avgPrice;
    @Column
    private LocalDate date;

    public GlobalOilEntity() {
    }

    @Builder
    public GlobalOilEntity(Integer globalOilPriceId, Double avgPrice, LocalDate date) {
        this.globalOilPriceId = globalOilPriceId;
        this.avgPrice = avgPrice;
        this.date = date;
    }
}
