package com.a701.nongstradamus.service;

import com.a701.nongstradamus.domain.DomesticOil;
import com.a701.nongstradamus.domain.DomesticOilDto;
import com.a701.nongstradamus.repository.DomesticOilRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DomesticOilServiceImpl implements DomesticOilService{

    @Autowired
    ModelMapper modelMapper;

//    @Autowired
//    private final DomesticOilRepository repository;

    @Override
    public DomesticOilDto getDomesticOil() {
        return null;
    }

    @Override
    public Integer setDomesticOil() {
        return null;
    }

    @Override
    public DomesticOilDto modifyDomesticOil() {
        return null;
    }

    @Override
    public Integer deleteDomesticOil() {
        return null;
    }
}
