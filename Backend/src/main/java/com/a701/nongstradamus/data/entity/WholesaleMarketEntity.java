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
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "wholesaleMarket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WholesaleMarketEntity {

    @Id
    @Column(name = "wholesaleMarketId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    @CreationTimestamp
    private Date date;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "originId")
    private OriginEntity origin;

}
