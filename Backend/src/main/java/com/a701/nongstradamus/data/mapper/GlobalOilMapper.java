package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.GlobalOilDto;
import com.a701.nongstradamus.data.entity.GlobalOilEntity;
import org.mapstruct.Mapper;

@Mapper
public interface GlobalOilMapper {
    public static GlobalOilMapper INSTANCE = new GlobalOilMapperImpl();

    GlobalOilEntity fromDtoToEntity(GlobalOilDto dto);
    GlobalOilDto fromEntityToDto(GlobalOilEntity entity);
}
