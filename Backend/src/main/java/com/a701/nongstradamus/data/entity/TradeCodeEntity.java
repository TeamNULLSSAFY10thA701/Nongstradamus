package com.a701.nongstradamus.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tradeCode")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeCodeEntity {

    @Id
    @Column(name = "productId")
    private Long id;

    @Column(name = "code")
    private String code;

}
