package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.PriceHistoryDto;
import com.a701.nongstradamus.data.entity.PriceHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceHistoryMapper {
    public static PriceHistoryMapper INSTANCE = Mappers.getMapper(PriceHistoryMapper.class);

    PriceHistoryEntity fromDtoToEntity(PriceHistoryDto priceHistoryDto);
}
