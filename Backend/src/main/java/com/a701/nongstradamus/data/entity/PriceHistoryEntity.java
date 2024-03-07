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
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "priceHistory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistoryEntity {

    @Id
    @Column(name = "priceHistoryId",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    @CreatedDate
    private Date date;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "ratio", nullable = false)
    private Double ratio;

    @Column(name = "grade", nullable = false)
    private Integer grade;

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;

}
