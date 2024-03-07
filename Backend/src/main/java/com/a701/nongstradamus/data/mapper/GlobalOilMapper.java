package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.GlobalOilDto;
import com.a701.nongstradamus.data.entity.GlobalOilEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GlobalOilMapper {
    public static GlobalOilMapper INSTANCE = Mappers.getMapper(GlobalOilMapper.class);

    GlobalOilEntity fromDtoToEntity(GlobalOilDto dto);
    GlobalOilDto fromEntityToDto(GlobalOilEntity entity);
}
