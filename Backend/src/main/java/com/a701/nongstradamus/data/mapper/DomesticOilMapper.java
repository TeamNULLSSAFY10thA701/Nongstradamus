package com.a701.nongstradamus.data.mapper;

import com.a701.nongstradamus.data.dto.DomesticOilDto;
import com.a701.nongstradamus.data.entity.DomesticOilEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DomesticOilMapper {
    public static DomesticOilMapper INSTANCE = new DomesticOilMapperImpl();

    DomesticOilEntity fromDtoToEntity(DomesticOilDto dto);
    DomesticOilDto fromEntityToDto(DomesticOilEntity entity);

}
