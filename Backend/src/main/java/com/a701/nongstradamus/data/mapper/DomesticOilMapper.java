package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.DomesticOilDto;
import com.a701.nongstradamus.data.entity.DomesticOilEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomesticOilMapper {
    public static DomesticOilMapper INSTANCE = Mappers.getMapper(DomesticOilMapper.class);

    DomesticOilEntity fromDtoToEntity(DomesticOilDto dto);
    DomesticOilDto fromEntityToDto(DomesticOilEntity entity);

}
