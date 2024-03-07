package com.a701.nongstradamus.data.dto;

import com.a701.nongstradamus.data.entity.OriginEntity;
import com.a701.nongstradamus.data.entity.ProductEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WholesaleMarketDto {

    private Date date;

    private Long price;

    private Integer grade;

    ProductEntity product;

    OriginEntity origin;
}
