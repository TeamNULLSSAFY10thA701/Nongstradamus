package com.a701.nongstradamus.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wholesaleMarketCode")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WholesaleMarketCodeEntity {

    @Id
    @Column(name = "productId", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;
}
