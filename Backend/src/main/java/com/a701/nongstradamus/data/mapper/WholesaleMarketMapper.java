package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.WholesaleMarketDto;
import com.a701.nongstradamus.data.entity.WholesaleMarketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WholesaleMarketMapper {
    public static WholesaleMarketMapper INSTANCE = Mappers.getMapper(WholesaleMarketMapper.class);

    WholesaleMarketEntity fromDtoToEntity(WholesaleMarketDto wholesaleMarketDto);
}
