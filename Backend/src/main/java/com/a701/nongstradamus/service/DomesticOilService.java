package com.a701.nongstradamus.service;

import com.a701.nongstradamus.domain.DomesticOilDto;

public interface DomesticOilService {
    public DomesticOilDto getDomesticOil();

    public Integer setDomesticOil();

    public DomesticOilDto modifyDomesticOil();

    public Integer deleteDomesticOil();
}
