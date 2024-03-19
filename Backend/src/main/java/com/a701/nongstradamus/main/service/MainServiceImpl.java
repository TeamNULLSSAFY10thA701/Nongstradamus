package com.a701.nongstradamus.main.service;

import com.a701.nongstradamus.common.CommonDto;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class MainServiceImpl implements MainService{

    @Override
    public CommonDto findHighestPriceDecreaseCrop() {

        return null;
    }

    @Override
    public CommonDto findHighestPriceIncreaseCrop() {
        return null;
    }

    @Override
    public CommonDto findPredictCard() {
        return null;
    }

    @Override
    public CommonDto findPresentCard() {
        return null;
    }
}
