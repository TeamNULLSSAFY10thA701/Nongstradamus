package com.a701.nongstradamus.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cardRecommend")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRecommendEntity {
    @Id
    @Column(name = "productId")
    private Long id;

    @Column(name = "productName")
    private String name;

    @Column(name = "productUnit")
    private String unit;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "priceToday")
    private Long priceToday;

    @Column(name = "priceTomorrow")
    private Long priceTomorrow;
}
