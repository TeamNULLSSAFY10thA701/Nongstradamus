package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.TradeDto;
import com.a701.nongstradamus.data.entity.TradeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TradeMapper {

    public static TradeMapper INSTANCE = Mappers.getMapper(TradeMapper.class);

    TradeEntity fromDtoToEntity(TradeDto tradeDto);

}
