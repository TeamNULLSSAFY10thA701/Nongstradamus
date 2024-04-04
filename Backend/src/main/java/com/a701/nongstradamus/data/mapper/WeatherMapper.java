package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.WeatherDto;
import com.a701.nongstradamus.data.entity.WeatherEntity;
import org.mapstruct.Mapper;

@Mapper
public interface WeatherMapper {
    public static WeatherMapper INSTANCE = new WeatherMapperImpl();

    WeatherEntity fromDtoToEntity(WeatherDto weatherDto);
}
