package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.WholesaleMarketDto;
import com.a701.nongstradamus.data.entity.WholesaleMarketEntity;
import org.mapstruct.Mapper;

@Mapper
public interface WholesaleMarketMapper {
    public static WholesaleMarketMapper INSTANCE = new WholesaleMarketMapperImpl();

    WholesaleMarketEntity fromDtoToEntity(WholesaleMarketDto wholesaleMarketDto);
}
