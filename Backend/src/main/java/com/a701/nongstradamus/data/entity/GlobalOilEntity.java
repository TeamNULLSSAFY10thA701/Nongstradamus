package com.a701.nongstradamus.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

}
