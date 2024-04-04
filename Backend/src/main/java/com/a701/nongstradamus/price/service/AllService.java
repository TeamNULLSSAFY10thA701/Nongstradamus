package com.a701.nongstradamus.price.service;

import com.a701.nongstradamus.common.CommonDto;

public interface AllService {
    CommonDto findProducts(Integer category);

    void resetResult();
}
