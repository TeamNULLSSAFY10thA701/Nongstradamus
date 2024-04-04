package com.a701.nongstradamus.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeEntity {

    @Id
    @Column(name = "tradeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tradeId;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;
}
