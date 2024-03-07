package com.a701.nongstradamus.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wholeslaeMarket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WholesaleMarketEntity {

    @Id
    @Column(name = "wholesaleMarketId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
