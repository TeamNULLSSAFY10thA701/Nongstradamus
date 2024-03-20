package com.a701.nongstradamus.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    @Column(name = "productId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productName", nullable = false)
    private String name;

    @Column(name = "productUnit")
    private String unit;

    @Column(name = "wholesaleMarketCode")
    private String wholesaleMarketCode;

    @Column(name = "tradeCode")
    private String tradeCode;

    @Column(name = "weatherCode")
    private String weatherCode;

    @Column(name = "category")
    private Integer category;

    @Column(name = "nickname")
    private String nickname;
}

