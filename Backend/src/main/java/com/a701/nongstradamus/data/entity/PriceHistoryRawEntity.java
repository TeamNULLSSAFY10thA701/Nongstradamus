package com.a701.nongstradamus.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "priceHistoryRaw")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistoryRawEntity {
    @Id
    @Column(name = "priceHistoryRawId")
    private Long id;

    @Column(name="date")
    private Date date;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "ratio")
    private Double ratio;

    @Column(name = "grade")
    private String grade;

    @Column(name="unit")
    private String unit;

    @Column(name = "isSaved")
    private Boolean isSaved;
}
