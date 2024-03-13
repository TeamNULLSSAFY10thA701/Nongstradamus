package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.OriginDto;
import com.a701.nongstradamus.data.entity.OriginEntity;
import org.mapstruct.Mapper;

@Mapper
public interface OriginMapper {

    public static OriginMapper INSTANCE = new OriginMapperImpl();

    OriginEntity fromDtoToEntity(OriginDto originDto);

}
