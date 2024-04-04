package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.TradeDto;
import com.a701.nongstradamus.data.entity.TradeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TradeMapper {

    public static TradeMapper INSTANCE = new TradeMapperImpl();

    TradeEntity fromDtoToEntity(TradeDto tradeDto);

}
