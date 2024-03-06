package com.a701.nongstradamus.data.domain;

import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;


@Data
@Entity
@Builder
@Getter
public class DomesticOilEntity {

    private static ModelMapper modelMapper;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer domesticOilPriceId;
    @Column
    private Double avgPrice;
    @Column
    private LocalDate date;

    public DomesticOilEntity() {
    }

    @Builder
    public DomesticOilEntity(Integer domesticOilPriceId, Double avgPrice, LocalDate date) {
        this.domesticOilPriceId = domesticOilPriceId;
        this.avgPrice = avgPrice;
        this.date = date;
    }
}
