package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.WeatherDto;
import com.a701.nongstradamus.data.entity.WeatherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WeatherMapper {
    public static WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    WeatherEntity fromDtoToEntity(WeatherDto weatherDto);
}
