package com.a701.nongstradamus.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WholesaleMarketMapper {
    public static WholesaleMarketMapper INSTANCE = Mappers.getMapper(WholesaleMarketMapper.class);
}
