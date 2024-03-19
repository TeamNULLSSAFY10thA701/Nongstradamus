package com.a701.nongstradamus.main.service;

import com.a701.nongstradamus.common.CommonDto;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public interface MainService {
    CommonDto findHighestPriceDecreaseCrop();
    CommonDto findHighestPriceIncreaseCrop();
    CommonDto findPredictCard();
    CommonDto findPresentCard();
}
