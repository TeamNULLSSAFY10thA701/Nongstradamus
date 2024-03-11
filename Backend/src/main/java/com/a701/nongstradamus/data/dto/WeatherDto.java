package com.a701.nongstradamus.data.dto;

import com.a701.nongstradamus.data.entity.OriginEntity;
import com.a701.nongstradamus.data.entity.ProductEntity;
import java.time.LocalDate;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {
    private LocalDate date;
    private int avgTemperature;
    private int maxTemperature;
    private int minTemperature;
    private int rain;
    private int wind;
    private int humidity;
    private int sunDuration;
    private ProductEntity product;
    private OriginEntity origin;
}
