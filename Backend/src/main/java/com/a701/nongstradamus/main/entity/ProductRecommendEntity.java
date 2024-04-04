package com.a701.nongstradamus.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productRecommend")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRecommendEntity {

    @Id
    @Column(name = "productId")
    private Long id;

    @Column(name = "productName")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "productUnit")
    private String unit;

    @Column(name = "priceToday")
    private Long priceToday;

    @Column(name = "priceHistory1")
    private Long priceHistory1;

    @Column(name = "priceHistory2")
    private Long priceHistory2;

    @Column(name = "priceHistory3")
    private Long priceHistory3;

    @Column(name = "priceHistory4")
    private Long priceHistory4;

    @Column(name = "priceHistory5")
    private Long priceHistory5;

    @Column(name = "priceHistory6")
    private Long priceHistory6;

    @Column(name = "priceHistory7")
    private Long priceHistory7;

    @Column(name = "pricePredict1")
    private Long pricePredict1;

    @Column(name = "pricePredict2")
    private Long pricePredict2;

    @Column(name = "pricePredict3")
    private Long pricePredict3;

    @Column(name = "pricePredict4")
    private Long pricePredict4;

    @Column(name = "pricePredict5")
    private Long pricePredict5;

    @Column(name = "pricePredict6")
    private Long pricePredict6;

}