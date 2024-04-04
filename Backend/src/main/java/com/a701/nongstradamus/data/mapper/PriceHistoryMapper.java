package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.PriceHistoryDto;
import com.a701.nongstradamus.data.entity.PriceHistoryEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PriceHistoryMapper {
    public static PriceHistoryMapper INSTANCE = new PriceHistoryMapperImpl();

    PriceHistoryEntity fromDtoToEntity(PriceHistoryDto priceHistoryDto);
}
