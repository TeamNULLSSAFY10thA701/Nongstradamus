package com.a701.nongstradamus.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wholeMarketCode")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WholeMarketCodeEntity {

    @Id
    @Column(name = "productId", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private Long code;
}
