package com.a701.nongstradamus.data.dto;

import com.a701.nongstradamus.data.entity.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeDto {
    private Integer balance;

    private Integer amount;

    private Integer volume;

    private Date date;

    private ProductEntity product;
}
