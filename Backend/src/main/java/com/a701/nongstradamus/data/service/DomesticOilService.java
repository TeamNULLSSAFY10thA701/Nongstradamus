package com.a701.nongstradamus.data.service;

import com.a701.nongstradamus.data.domain.DomesticOilEntity;
import com.a701.nongstradamus.data.domain.DomesticOilDto;
import com.a701.nongstradamus.data.repository.DomesticOilRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DomesticOilService {

    private final ModelMapper modelMapper;
    private final DomesticOilRepository repository;


    public DomesticOilDto getDomesticOilByDate(DomesticOilEntity domesticOil) {

        return null;
    }

    public List<DomesticOilDto> getAllDomesticOil() {

        List<DomesticOilEntity> lst = repository.findAll();
        List<DomesticOilDto> result =
                lst.stream().map(p -> modelMapper.map(p, DomesticOilDto.class)).collect(Collectors.toList());
        return result;
    }

    public DomesticOilDto createDomesticOil(DomesticOilDto dto) {

        DomesticOilEntity entity = modelMapper.map(dto, DomesticOilEntity.class);

        repository.save(entity);

        return dto;
    }

    public DomesticOilDto updateDomesticOilByDate(DomesticOilDto dto) {

        return null;
    }

    public DomesticOilDto deleteDomesticOilByDate() {

        return null;
    }
}
