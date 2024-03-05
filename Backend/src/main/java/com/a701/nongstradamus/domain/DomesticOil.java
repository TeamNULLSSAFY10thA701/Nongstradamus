package com.a701.nongstradamus.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;


@Entity
@Data
@Getter
public class DomesticOil {

    private static ModelMapper modelMapper;

    @Id @GeneratedValue @Column
    private Integer domesticOilPriceId;
    @Column
    private Integer avgPrice;
    @Column
    private LocalDate date;

    public DomesticOil() {
    }

    @Builder
    public DomesticOil(Integer domesticOilPriceId, Integer avgPrice, LocalDate date) {
        this.domesticOilPriceId = domesticOilPriceId;
        this.avgPrice = avgPrice;
        this.date = date;
    }
}
