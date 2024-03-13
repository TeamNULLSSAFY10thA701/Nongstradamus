package com.a701.nongstradamus.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@Entity
@Table(name="domesticOilPrice")
@NoArgsConstructor
@AllArgsConstructor
public class DomesticOilEntity {

    @Id
    @Column(name = "domesticOilPriceId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer domesticOilPriceId;

    @Column(name = "dieselPrice")
    private Double dieselPrice;

    @Column(name = "kerosenePrice")
    private Double kerosenePrice;

    @Column(name = "gasolinePrice")
    private Double gasolinePrice;

    @Column(name = "date")
    private LocalDate date;

}
