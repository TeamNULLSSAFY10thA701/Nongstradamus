package com.a701.nongstradamus.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WholeMarketMapper {
    public static WholeMarketMapper INSTANCE = Mappers.getMapper(WholeMarketMapper.class);
}
