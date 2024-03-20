package com.a701.nongstradamus.data.dto;

import com.a701.nongstradamus.data.entity.OriginEntity;
import com.a701.nongstradamus.data.entity.ProductEntity;
import com.a701.nongstradamus.data.entity.WeatherOriginCodeEntity;
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
    private Integer avgTemperature;
    private Integer maxTemperature;
    private Integer minTemperature;
    private Integer rain;
    private Integer wind;
    private Integer humidity;
    private Integer sunDuration;
    private ProductEntity product;
    private WeatherOriginCodeEntity origin;
}
