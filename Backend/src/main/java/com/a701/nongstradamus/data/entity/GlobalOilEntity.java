package com.a701.nongstradamus.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "globalOilPrice")
@NoArgsConstructor
@AllArgsConstructor
public class GlobalOilEntity {

    @Id
    @Column(name = "globalOilPriceId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer globalOilPriceId;

    @Column(name = "wtiPrice")
    private Double wtiPrice;

    @Column(name = "brentPrice")
    private Double brentPrice;

    @Column(name = "dieselPrice")
    private Double dxlPrice;

    @Column(name = "gasolinePrice")
    private Double gasolinePrice;

    @Column(name = "date")
    private LocalDate date;

}
