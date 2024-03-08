package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.OriginDto;
import com.a701.nongstradamus.data.entity.OriginEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OriginMapper {

    public static OriginMapper INSTANCE = Mappers.getMapper(OriginMapper.class);

    OriginEntity fromDtoToEntity(OriginDto originDto);

}
