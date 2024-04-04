package com.a701.nongstradamus.main.service;

import com.a701.nongstradamus.common.CommonDto;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public interface MainService {
    CommonDto findHighestRatioDecreaseProduct() throws EntityNotFoundException;
    CommonDto findHighestRatioIncreaseProduct() throws EntityNotFoundException;
    CommonDto findPredictCard() throws EntityNotFoundException;
    CommonDto findPresentCard() throws EntityNotFoundException;
    CommonDto findPastCard() throws EntityNotFoundException;

    void resetData();

    CommonDto findTodayRecommendData();

    CommonDto findTomorrowRecommendData();

    CommonDto listBestRecommendCards();
}
